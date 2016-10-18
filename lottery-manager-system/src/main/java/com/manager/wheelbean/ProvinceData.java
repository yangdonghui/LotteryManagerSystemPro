package com.manager.wheelbean;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import kankan.wheel.model.CityModel;
import kankan.wheel.model.DistrictModel;
import kankan.wheel.model.ProvinceModel;
import kankan.wheel.service.XmlParserHandler;

/**
 * @author donghuiyang
 * @create time 2016/6/21 0021.
 */
public class ProvinceData {
    Context context;

    /**
     * 所有省
     */
    protected String[] mProvinceDatas;
    /**
     * key - 省 value - 市
     */
    protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    /**
     * key - 市 values - 区
     */
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

    /**
     * key - 区 values - 邮编
     */
    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

    /**
     * 当前省的名称
     */
    protected String mCurrentProviceName = "";
    /**
     * 当前市的名称
     */
    protected String mCurrentCityName = "";
    /**
     * 当前区的名称
     */
    protected String mCurrentDistrictName = "";

    /**
     * 当前区的邮政编码
     */
    protected String mCurrentZipCode;

    public ProvinceData(Context context) {
        this.context = context;

        initProvinceDatas();
    }

    /**
     * 解析省市区的XML数据
     */
    protected void initProvinceDatas()
    {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = context.getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            //*/ 初始化默认选中的省、市、区
            if (provinceList!= null && !provinceList.isEmpty()) {
                /*mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList!= null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }*/
            }
            //*/
            assert provinceList != null;
            mProvinceDatas = new String[provinceList.size()];
            for (int i=0; i< provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j=0; j< cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k=0; k<districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    public String[] getProvinceDatas() {
        return mProvinceDatas;
    }

    public Map<String, String[]> getCitisDatasMap() {
        return mCitisDatasMap;
    }

    public Map<String, String[]> getDistrictDatasMap() {
        return mDistrictDatasMap;
    }

    public Map<String, String> getZipcodeDatasMap() {
        return mZipcodeDatasMap;
    }

    public String getCurrentProviceName() {
        return mCurrentProviceName;
    }

    public String getCurrentCityName() {
        return mCurrentCityName;
    }

    public String getCurrentDistrictName() {
        return mCurrentDistrictName;
    }

    public String getCurrentZipCode() {
        return mCurrentZipCode;
    }

    public ProvinceData setProvinceDatas(String[] mProvinceDatas) {
        this.mProvinceDatas = mProvinceDatas;
        return this;
    }

    public ProvinceData setCitisDatasMap(Map<String, String[]> mCitisDatasMap) {
        this.mCitisDatasMap = mCitisDatasMap;
        return this;
    }

    public ProvinceData setDistrictDatasMap(Map<String, String[]> mDistrictDatasMap) {
        this.mDistrictDatasMap = mDistrictDatasMap;
        return this;
    }

    public ProvinceData setZipcodeDatasMap(Map<String, String> mZipcodeDatasMap) {
        this.mZipcodeDatasMap = mZipcodeDatasMap;
        return this;
    }

    public ProvinceData setCurrentProviceName(String mCurrentProviceName) {
        this.mCurrentProviceName = mCurrentProviceName;
        return this;
    }

    public ProvinceData setCurrentCityName(String mCurrentCityName) {
        this.mCurrentCityName = mCurrentCityName;
        return this;
    }

    public ProvinceData setCurrentDistrictName(String mCurrentDistrictName) {
        this.mCurrentDistrictName = mCurrentDistrictName;
        return this;
    }

    public ProvinceData setCurrentZipCode(String mCurrentZipCode) {
        this.mCurrentZipCode = mCurrentZipCode;
        return this;
    }

    /**
     * 设置当前数据
     * @param provice
     * @param city
     * @param area
     */
    public void setCurData(String provice, String city, String area) {
        mCurrentProviceName = provice;
        mCurrentCityName = city;
        mCurrentDistrictName = area;

        Log.e("","");
    }

    /**
     * 获取省 市 区 所在索引值
     * @return
     */
    public int getCurProviceNameIndex() {
        int length = getProvinceDatas().length;
        for (int i=0;i<length;i++){
            String name = getProvinceDatas()[i].trim();
            String cur = getCurrentProviceName().trim();
            if (name.equals("北京市")){
                return i;
            }
        }

        return 0;
    }
    public int getCurCityNameIndex() {
        String[] cities = getCitisDatasMap().get(getCurrentProviceName());
        int length = cities.length;

        for (int i=0;i<length;i++){
            String name = cities[i].trim();
            String cur = getCurrentCityName().trim();
            if (name.equals(cur)){
                return i;
            }
        }
        return 0;
    }
    public int getCurDistrictNameIndex() {
        String[] areas = getDistrictDatasMap().get(getCurrentCityName());
        int length = areas.length;

        for (int i=0;i<length;i++){
            String name = areas[i].trim();
            String cur = getCurrentDistrictName().trim();
            if (name.equals(cur)){
                return i;
            }
        }
        return 0;
    }
}
