package com.manager.common;

import com.manager.bean.AddressBean;
import com.manager.bean.BankBean;
import com.manager.bean.BettingshopBean;
import com.manager.bean.ChartBean;
import com.manager.bean.ChatBean;
import com.manager.bean.ChatMessageBean;
import com.manager.bean.CommunityListBean;
import com.manager.bean.DeclareConsumableBean;
import com.manager.bean.DeclareFaultBean;
import com.manager.bean.DeclareOrdersBean;
import com.manager.bean.FriendBean;
import com.manager.bean.InfoBean;
import com.manager.bean.ItemBean;
import com.manager.bean.LotteryInfoBean;
import com.manager.bean.NewsBean;
import com.manager.bean.NoteLotterybean;
import com.manager.bean.NumberBean;
import com.manager.bean.OrdersBean;
import com.manager.bean.PayRecordBean;
import com.manager.bean.ProductBean;
import com.manager.bean.SingleTicketBean;
import com.manager.bean.UserBean;
import com.manager.bean.UserDeclareBean;
import com.manager.bean.UserSelectNumberBean;
import com.manager.bean.WidgetBean;
import com.manager.bean.WidgetBean1;
import com.manager.lotterypro.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/2/25 0025.
 */
public class Constants {
    //相机临时存储
    public static final String TMP_PATH = "clip_temp.jpg";

    //tab标签类型
    public static final int HOME_TAB = 0;
    public static final int COMMUNITY_TAB = 1;
    public static final int NEW_TAB = 2;
    public static final int LOTTERY_TAB = 3;
    public static final int ME_TAB = 4;

    //默认类型
    public static final int LayoutDefault = -1;
    //界面类型
    public static final int EntrustBetOrderConfirmActLayoutType = 0;//选择投注 被委托人  订单详情 修改为委托人
    public static final int LotteryEntrustRecordDeatilActLayoutType = 1;
    public static final int BettingshopEntrustRecordDeatilLayoutType = 2;

    //管理员 订单管理 界面类型
    public static final int ManagerOrderBillingActLayoutType = 0;//即开票订单
    public static final int ManagerOrderFaultActLayoutType = 1;    //故障报修 订单
    public static final int ManagerOrderConsumableActLayoutType = 2;//耗材 订单

    //彩种列表界面的来源
    public static final int LotteryIntroduceActLayoutType = 3;//玩法介绍
    public static final int EntrustServiceActLayoutType = 4;  //委托服务

    public static final int TextStyles[] = {
            R.style.text_size_9_text_color_2,
            R.style.text_size_9_text_color_16,
            R.style.text_size_9_text_color_2,
            R.style.text_size_9_text_color_16,
            R.style.text_size_9_text_color_2,
    };

    public static final String money_str = "¥";

    //主tab文字
    public final static int MainTabStrs[] = {
            R.string.home_title,
            R.string.comunity_title,
            R.string.lotterycity_title,
            R.string.me_title
    };
    public final static int MainTabImg[] = {
            R.drawable.icon_home_tab,
            R.drawable.icon_community_tab,
            R.drawable.icon_lotterycity_tab,
            R.drawable.icon_me_tab
    };

    //开中奖信息
    public final static String[] NewsTab = {
            "开奖信息",
            "中奖信息",
    };
    public final static String[] NewsTab1 = {
            "开奖信息",
            "促销信息",
            "通知"
    };

    //彩票城
    public final static ArrayList<WidgetBean1> LotteryCityGridViewLists = new ArrayList<WidgetBean1>(){
        {
            add(new WidgetBean1("1",R.mipmap.lottery_city_nav_01,"玩法介绍"));
            add(new WidgetBean1("0",R.mipmap.lottery_city_nav_03,"彩票助手"));
            add(new WidgetBean1("2",R.mipmap.lottery_city_nav_02,"推荐号"));
            add(new WidgetBean1("3",R.mipmap.lottery_city_nav_04,"故障申报"));
            add(new WidgetBean1("4",R.mipmap.lottery_city_nav_05,"耗材"));
            add(new WidgetBean1("5",R.mipmap.lottery_city_nav_06,"即开票"));
        }
    };

    //我
    public final static ArrayList<WidgetBean1> MeGridViewLists1 = new ArrayList<WidgetBean1>(){
        {
            add(new WidgetBean1("0",R.mipmap.me_icon1,"我的钱包"));
            add(new WidgetBean1("1",R.mipmap.me_icon2,"我的彩票"));
            add(new WidgetBean1("2",R.mipmap.me_icon3,"设置"));
        }
    };
    public final static ArrayList<WidgetBean1> MeGridViewLists2 = new ArrayList<WidgetBean1>(){
        {
            add(new WidgetBean1("0",R.mipmap.me_icon1,"我的钱包"));
            add(new WidgetBean1("0",R.mipmap.me_icon1,"雇员管理"));
            add(new WidgetBean1("1",R.mipmap.me_icon3,"设置"));
        }
    };
    public final static ArrayList<WidgetBean1> MeGridViewLists3 = new ArrayList<WidgetBean1>(){
        {
            add(new WidgetBean1("0",R.mipmap.me_icon1,"我的钱包"));
            add(new WidgetBean1("0",R.mipmap.me_icon1,"我的投注站"));
            add(new WidgetBean1("1",R.mipmap.me_icon3,"设置"));
        }
    };

    public static final String IconTexts1[] = {
            "开始受理",
            "指定维修人员",
            "上门服务中",
            "维修完成"
    };

    public static final String IconTexts2[] = {
            "完成支付",
            "已出库",
            "派送中",
            "签收"
    };

    public static final String IconTexts3[] = {
            "已支付",
            "站点已接受委托",
            "站点已出票",
            "彩民已取票",
            "已完成"
    };

    public static final String PayIconTexts[] = {
            "付款",
            "收款",
            "余额",
            "银行卡",
            "添加银行卡"
    };
    public final static String PayMenuText[] = {
            "充值",
            "提现",
            "转账",
            "站点缴费"
    };

    //钱包菜单栏
    public final static ArrayList<WidgetBean1> LotteryWalletGridViewLists = new ArrayList<WidgetBean1>() {
        {
            add(new WidgetBean1("0",R.mipmap.pay_nav_04, PayMenuText[0]));
            add(new WidgetBean1("1",R.mipmap.pay_nav_05, PayMenuText[1]));
            add(new WidgetBean1("2",R.mipmap.pay_nav_06, PayMenuText[2]));
            /*add(new WidgetBean1("3",R.mipmap.pay_nav_07, ""));*/
        }
    };
    public final static ArrayList<WidgetBean1> BettingshopWalletGridViewLists = new ArrayList<WidgetBean1>() {
        {
            add(new WidgetBean1("0",R.mipmap.pay_nav_04, PayMenuText[0]));
            add(new WidgetBean1("1",R.mipmap.pay_nav_05, PayMenuText[1]));
            add(new WidgetBean1("2",R.mipmap.pay_nav_06, PayMenuText[2]));
            add(new WidgetBean1("1",R.mipmap.pay_nav_100, PayIconTexts[0]));
            add(new WidgetBean1("2",R.mipmap.pay_nav_09, PayMenuText[3]));
            /*add(new WidgetBean1("3",R.mipmap.pay_nav_07, ""));*/
        }
    };

    public final static ArrayList<WidgetBean1> WalletMenus1 = new ArrayList<WidgetBean1>() {
        {
            add(new WidgetBean1("0",R.mipmap.pay_nav_01, PayIconTexts[0]));
            add(new WidgetBean1("1",R.mipmap.pay_nav_02, PayIconTexts[2]));
            add(new WidgetBean1("2",R.mipmap.pay_nav_03, PayIconTexts[3]));
        }
    };
    public final static ArrayList<WidgetBean1> WalletMenus2 = new ArrayList<WidgetBean1>() {
        {
            add(new WidgetBean1("0",R.mipmap.pay_nav_08, PayIconTexts[1]));
            add(new WidgetBean1("1",R.mipmap.pay_nav_02, PayIconTexts[2]));
            add(new WidgetBean1("2",R.mipmap.pay_nav_03, PayIconTexts[3]));
        }
    };

    //消息中的gridview数据列表
    public final static ArrayList<WidgetBean> NewsGridViewLists = new ArrayList<WidgetBean>() {
        {
            add(new WidgetBean(R.mipmap.message_btn_01, R.string.news_item_str_00));
            add(new WidgetBean(R.mipmap.message_btn_02, R.string.news_item_str_1));
            add(new WidgetBean(R.mipmap.message_btn_03, R.string.news_item_str_2));
            add(new WidgetBean(R.mipmap.message_btn_04, R.string.news_item_str_3));
            add(new WidgetBean(R.mipmap.message_btn_05, R.string.news_item_str_41));
            add(new WidgetBean(R.mipmap.message_btn_06, R.string.news_item_str_5));
        }
    };

    //我的gridview数据列表
    public final static ArrayList<WidgetBean> MeLotteryGridViewLists = new ArrayList<WidgetBean>() {
        {
            add(new WidgetBean(R.mipmap.me_btn_01, R.string.me_item_str_0));
            /*add(new WidgetBean(R.mipmap.me_btn_02, R.string.me_item_str_1));*/
            add(new WidgetBean(R.mipmap.me_btn_03, R.string.me_item_str_2));
            /*add(new WidgetBean(R.mipmap.me_btn_04, R.string.me_item_str_3));*/
        }
    };

    public final static ArrayList<WidgetBean> MeBettingshopGridViewLists = new ArrayList<WidgetBean>() {
        {
            add(new WidgetBean(R.mipmap.me_btn_01, R.string.me_item_str_0));
            /*add(new WidgetBean(R.mipmap.me_btn_02, R.string.me_item_str_1));*/
            /*add(new WidgetBean(R.mipmap.me_btn_03, R.string.me_item_str_2));*/
            /*add(new WidgetBean(R.mipmap.me_btn_04, R.string.me_item_str_3));
            add(new WidgetBean(R.mipmap.me_btn_05, R.string.me_item_str_4));
            add(new WidgetBean(R.mipmap.me_btn_07, R.string.me_item_str_5));*/
            add(new WidgetBean(R.mipmap.me_zd_btn_02, R.string.me_item_str_6));
            add(new WidgetBean(R.mipmap.me_zd_btn_02, R.string.setting_title));
        }
    };

    public final static ArrayList<WidgetBean> MeManagerGridViewLists = new ArrayList<WidgetBean>() {
        {
            add(new WidgetBean(R.mipmap.me_btn_01, R.string.me_item_str_0));
            /*add(new WidgetBean(R.mipmap.me_btn_02, R.string.me_item_str_1));*/
            /*add(new WidgetBean(R.mipmap.me_btn_03, R.string.me_item_str_2));*/
            /*add(new WidgetBean(R.mipmap.me_btn_04, R.string.me_item_str_3));*/
            /*add(new WidgetBean(R.mipmap.me_btn_07, R.string.me_item_str_5));*/
            add(new WidgetBean(R.mipmap.me_gul_btn_01, R.string.me_item_str_7));
            add(new WidgetBean(R.mipmap.me_zd_btn_02, R.string.setting_title));
        }
    };

    public final static String[] LotteryEntrustTab = {
            "待付款",
            "待处理",
            "已完成"
    };
    public final static String[] BettingshopEntrustTab = {
            "待处理",
            "待领票",
            "已完成"
    };

    public final static String[] ManagerConsumableTab = {
            "全部订单",
            "打包",
            "装车",
            "配送"
    };

    public final static String[] ManagerFaultTab = {
            "全部订单",
            "等待中",
            "维修中",
            "已完成"
    };

    public final static String[] SiteRecordTab = {
            "待受理",
            "服务中",
            "已完成"
    };

    public final static String[] BillingRecordTab = {
            "待发货",
            "待签收",
            "已完成"
    };

    //走势图文字
    public final static String[] ChartTab1 = {
            "红球走势",
            "蓝球走势"
    };

    public final static ArrayList<Integer> ssqRedChartHeaderItemLits = new ArrayList<Integer>(){
        {
            add(1);add(2);add(3);add(4);add(5);add(6);
            add(7);add(8);add(9);add(10);add(11);add(12);
            add(13);add(14);add(15);add(16);add(17);
            add(18);add(19);add(20);add(21);add(22);
            add(23);add(24);add(25);add(26);add(27);add(28);
            add(29);add(30);add(31);add(32);add(33);
        }
    };
    public final static ArrayList<Integer> ssqBlueChartHeaderItemLits = new ArrayList<Integer>(){
        {
            add(1);add(2);add(3);add(4);add(5);
            add(6);add(7);add(8);add(9);add(10);
            add(11);add(12);add(13);add(14);add(15);
            add(16);
        }
    };
    public final static ArrayList<Integer> SsqRedNumberLists = new ArrayList<Integer>(){
        {
            add(1);add(1);add(1);add(1);add(1);add(1);
            add(1);add(1);add(1);add(1);add(1);add(1);
            add(1);add(1);add(1);add(1);add(1);add(1);
            add(1);add(1);add(1);add(1);add(1);add(1);
            add(1);add(1);add(1);add(1);add(1);add(1);
            add(1);add(1);add(1);
        }
    };
    public final static ArrayList<Integer> SsqBlueNumberLists = new ArrayList<Integer>(){
        {
            add(1);add(1);add(1);add(1);add(1);
            add(1);add(1);add(1);add(1);add(1);
            add(1);add(1);add(1);add(1);add(1);
            add(1);

        }
    };

    public final static ArrayList<ChartBean> SsqChartLists = new ArrayList<ChartBean>(){
        {
            add(new ChartBean("071期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("070期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("069期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("068期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("067期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("066期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("065期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("064期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("063期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("062期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("061期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("060期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("059期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("058期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("057期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("056期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("055期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("054期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("053期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("052期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("051期", SsqRedNumberLists, SsqBlueNumberLists));
            add(new ChartBean("050期", SsqRedNumberLists, SsqBlueNumberLists));
        }
    };

    //申报数据
    public final static ArrayList<UserDeclareBean> DeclareLists = new ArrayList<UserDeclareBean>() {
        {
            add(new UserDeclareBean(0,"2016-06-11-0004", "故障申报", "2016-01-31", "打印机", "XXXXXXXXXXXXXXXXXXX", "未受理"));
            add(new UserDeclareBean(1,"2016-06-11-0003", "耗材申报", "2016-01-31", "宣传纸", "XXXXXXXXXXXXXXXXXXX", "进行中"));
            add(new UserDeclareBean(2,"2016-06-11-0002", "故障申报", "2016-01-31", "扫描仪", "XXXXXXXXXXXXXXXXXXX", "进行中"));
        }
    };

    //公告
    public final static ArrayList<NewsBean> AnnouncementLists = new ArrayList<NewsBean>() {
        {
            add(new NewsBean("0","预开票已发售请及时预订", "2016年第一期预开票已开始发售, 因数量有限, 请各位站点业主及时预订。", "01月26日   12:15", "1", "0", "管理员"));
            add(new NewsBean("1","预开票已发售请及时预订", "2016年第一期预开票已开始发售, 因数量有限, 请各位站点业主及时预订。", "01月26日   12:15", "1", "0", "管理员"));
            add(new NewsBean("2","预开票已发售请及时预订", "2016年第一期预开票已开始发售, 因数量有限, 请各位站点业主及时预订。", "01月26日   12:15", "1", "0", "管理员"));
        }
    };

    //活动
    public final static ArrayList<NewsBean> ActivitiesLists = new ArrayList<NewsBean>() {
        {
            add(new NewsBean("0","预开票已发售请及时预订", "2016年1月20日 - 2016年5月20日", "01月26日   12:15", "2016年第一期预开票已开始发售, 因数量有限, 请各位站点业主及时预订。", R.mipmap.debug_nav_07, "2016年1月20日"));
            add(new NewsBean("1","预开票已发售请及时预订", "2016年1月20日 - 2016年5月20日", "01月26日   12:15", "2016年第一期预开票已开始发售, 因数量有限, 请各位站点业主及时预订。",R.mipmap.debug_nav_08, "2016年1月20日"));
            add(new NewsBean("2","预开票已发售请及时预订", "2016年1月20日 - 2016年5月20日", "01月26日   12:15","2016年第一期预开票已开始发售, 因数量有限, 请各位站点业主及时预订。", R.mipmap.debug_nav_09, "2016年1月20日"));
        }
    };

    //资讯
    public final static ArrayList<InfoBean> InfosLists = new ArrayList<InfoBean>() {
        {
            add(new InfoBean("0","彩民理性购彩 机选中大奖", "全国仅中出1注单注奖金1.72亿元的一等奖，在成都出现。", R.mipmap.debug_nav_11));
            add(new InfoBean("1","彩民理性购彩 机选中大奖", "全国仅中出1注单注奖金1.72亿元的一等奖，在成都出现。", R.mipmap.debug_nav_11));
            add(new InfoBean("2","彩民理性购彩 机选中大奖", "全国仅中出1注单注奖金1.72亿元的一等奖，在成都出现。", R.mipmap.debug_nav_11));
        }
    };

    //彩票城
    public final static ArrayList<WidgetBean1> LotteryCityLists1 = new ArrayList<WidgetBean1>(){
        {
            add(new WidgetBean1("0", R.mipmap.lottery_icon_ssq, "双色球", 6, "至少选择6个", 1, "至少选择1个", 33, 16));
            add(new WidgetBean1("1", R.mipmap.lottery_icon_qlc, "七乐彩", 5, "至少选择5个", 2, "至少选择2个", 35, 12));
            add(new WidgetBean1("2", R.mipmap.lottery_icon_3d, "3D", 0, "", 0, "", 0, 0));
        }
    };

    public final static ArrayList<WidgetBean1> LotteryCityLists11 = new ArrayList<WidgetBean1>(){
        {
            add(new WidgetBean1("0", R.drawable.lottery_info_nav_01, "双色球"));
            add(new WidgetBean1("1", R.drawable.lottery_info_nav_02, "七乐彩"));
            add(new WidgetBean1("2", R.drawable.lottery_info_nav_03, "3D"));
        }
    };

    public final static ArrayList<WidgetBean1> LotteryCityLists2 = new ArrayList<WidgetBean1>(){
        {
            add(new WidgetBean1("0", R.drawable.billing_nav_07, "双色球"));
            add(new WidgetBean1("1", R.drawable.billing_nav_07, "七乐彩"));
            add(new WidgetBean1("2", R.drawable.billing_nav_07, "3D"));
        }
    };

    //推荐号数据
    public final static List<Integer> RecommendLists = new ArrayList<Integer>(Arrays.asList(R.drawable.lottery_info_nav_01, R.drawable.lottery_info_nav_02, R.drawable.lottery_info_nav_03));

    //开奖信息
    public final static  ArrayList<LotteryInfoBean> AllLotteryLists = new ArrayList<LotteryInfoBean>(){
        {
            add(new LotteryInfoBean(0,0,"双色球","第2016055期","2016年05月15日 (周四)", new ArrayList<String>(){{add("05"); add("06");add("10");add("16");add("22");add("26");}}, new ArrayList<String>(){{add("11"); }}));
            add(new LotteryInfoBean(0,1,"七乐彩","第16056期","2016年1月25日 (周三)", new ArrayList<String>(){{add("06"); add("09");add("15");add("21");add("25");}}, new ArrayList<String>(){{add("05"); add("11");}}));
        }
    };
    //双色球数据
    public final static  ArrayList<LotteryInfoBean> ShuangseqiuLotteryLists = new ArrayList<LotteryInfoBean>(){
        {
            add(new LotteryInfoBean(0,0,"双色球","第2016055期","2016年05月15日 (周四)", new ArrayList<String>(){{add("05"); add("06");add("10");add("16");add("22");add("26");}}, new ArrayList<String>(){{add("11"); }}));
            add(new LotteryInfoBean(1,0,"双色球","第2016055期","2016年01月25日 (周四)", new ArrayList<String>(){{add("05"); add("06");add("10");add("16");add("22");add("26");}}, new ArrayList<String>(){{add("07"); }}));
        }
    };
    //大乐透数据
    public final static  ArrayList<LotteryInfoBean> DaletouLotteryLists = new ArrayList<LotteryInfoBean>(){
        {
            add(new LotteryInfoBean(0,0,"七乐彩","第2016081期","2016年07月13日 (周三)", new ArrayList<String>(){{add("04"); add("11");add("14");add("18");add("21");add("22");add("29");}}, new ArrayList<String>(){{add("20"); }}));
            add(new LotteryInfoBean(1,0,"七乐彩","第2016080期","2016年07月11日 (周一)", new ArrayList<String>(){{add("06"); add("07");add("12");add("14");add("15");add("16");add("23");}}, new ArrayList<String>(){{add("01"); }}));
        }
    };

    //双色球走势
    public final static ArrayList<ChartBean> ssqRedLists = new ArrayList<ChartBean>() {
        {

        }
    };

    //社区列表数据
    public final static ArrayList<CommunityListBean> CommunityLists = new ArrayList<CommunityListBean>() {
        {
            add(new CommunityListBean("0",R.mipmap.debug_icon_1, "体彩社区", "一年一度新彩票发售", "561"));
            add(new CommunityListBean("1",R.mipmap.debug_icon_1, "体彩社区", "一年一度新彩票发售", "10"));
            add(new CommunityListBean("2",R.mipmap.debug_icon_1, "体彩社区", "一年一度新彩票发售", "561"));
            add(new CommunityListBean("3",R.mipmap.debug_icon_1, "体彩社区", "一年一度新彩票发售", "10"));
            add(new CommunityListBean("4",R.mipmap.debug_icon_1, "体彩社区", "一年一度新彩票发售", "561"));
            add(new CommunityListBean("5",R.mipmap.debug_icon_1, "体彩社区", "一年一度新彩票发售", "10"));
            add(new CommunityListBean("6",R.mipmap.debug_icon_1, "体彩社区", "一年一度新彩票发售", "561"));
            add(new CommunityListBean("7",R.mipmap.debug_icon_1, "体彩社区", "一年一度新彩票发售", "10"));
        }
    };

    //热门搜索 数据列表
    public final static ArrayList<String> searchLists = new ArrayList<String>() {
        {
            add("福彩1");
            add("福彩2");
            add("福彩3");
            add("福彩4");
            add("福彩5");
            add("福彩6");
        }
    };

    //通讯录数据
    public final static ArrayList<FriendBean> cantactLists = new ArrayList<FriendBean>(){
        {
            add(new FriendBean("0", "耿琦", "http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 4));
            add(new FriendBean("1", "王宝强", "http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 4));
            add(new FriendBean("2", "柳岩", "http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 4));
            add(new FriendBean("3", "文章", "http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 4));
            add(new FriendBean("4", "马伊琍", "http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 4));

            add(new FriendBean("6", "a耿琦", "http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 4));
            add(new FriendBean("7", "a王宝强", "http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 4));
            add(new FriendBean("8", "c柳岩", "http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 4));
            add(new FriendBean("9", "c文章", "http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 4));
            add(new FriendBean("10", "f马伊琍", "http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 4));
        }
    };

    //添加好友列表
    public final static ArrayList<FriendBean> FriendHistoryLists = new ArrayList<FriendBean>() {
        {
            add(new FriendBean("0","管理员1", "哈哈哈.....","http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 0, "接受", ""));
            add(new FriendBean("1","管理员2", "哈哈哈.....","http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 1, "添加", ""));

            add(new FriendBean("3","管理员1", "哈哈哈.....","http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 2, "等待验证", ""));
            add(new FriendBean("4","管理员2", "哈哈哈.....","http://www.feizl.com/upload2007/2014_06/1406272351394618.png", 3, "已添加", ""));
        }
    };

    //即开票产品列表
    public final static ArrayList<ProductBean> BillingLists = new ArrayList<ProductBean>() {
        {
            add(new ProductBean("0",1,"", "福彩1111", "600","本", "20"));
            add(new ProductBean("1",1,"", "福彩2222", "600","本", "20"));
            add(new ProductBean("2",1,"", "福彩3333", "600","本", "20"));
        }
    };

    //耗材产品列表
    public final static ArrayList<ProductBean> ConsumableLists = new ArrayList<ProductBean>() {
        {
            add(new ProductBean("0",2,"", "打印纸", "2","卷","5"));
            add(new ProductBean("1",2,"", "十月海报", "10","张","5"));
            add(new ProductBean("2",2,"", "双色球宣传海报", "10","张","5"));
            add(new ProductBean("3",2,"", "投注单", "10","包","5"));
        }
    };

    //筛选条件
    public final static ArrayList<String> filterLists = new ArrayList<String>(){
        {
            add("销售排行");
            add("促销品种");
            add("50元面值");
            add("30元面值");
            add("20元面值");
            add("10元面值");
            add("5元面值");
            add("2元面值");
        }
    };

    //订单记录
    public final static List<ItemBean> BillingOrderLists = new ArrayList<ItemBean>() {
        {
            add(new ItemBean("0","福彩1111", 5, 10));
            add(new ItemBean("1","福彩1112", 1, 2));
            add(new ItemBean("2","福彩1113", 5, 100));
        }
    };

    public final static ArrayList<OrdersBean> BillingRecordLists = new ArrayList<OrdersBean>() {
        {
            add(new OrdersBean("0","2016-06-01-1122", "福彩1111等", "2016-06-01 10:00", 0, "打包备货中", "600", Constants.BillingOrderLists));
            add(new OrdersBean("1","2016-06-05-1122", "福彩2222等", "2016-06-01 10:00", 1,"装车中", "600", Constants.BillingOrderLists));
            add(new OrdersBean("2","2016-06-09-1122", "福彩3333等", "2016-06-01 10:00", 2, "配送中", "600", Constants.BillingOrderLists));
            add(new OrdersBean("2","2016-06-09-1122", "福彩3333等", "2016-06-01 10:00", 2, "配送中", "600", Constants.BillingOrderLists));
        }
    };

    //购物车
    public final static ArrayList<ProductBean> CartLists = new ArrayList<ProductBean>() {
        {
            add(new ProductBean("0",1,"", "福彩1111", "600","20元","本", "", ""));
            add(new ProductBean("1",1,"", "福彩2222", "600","20元","本", "", ""));
            add(new ProductBean("2",1,"", "福彩3333", "600","20元","本", "", ""));
        }
    };

    //地址
    public final static ArrayList<AddressBean> AddressLists = new ArrayList<AddressBean>() {
        {
            add(new AddressBean("0","张三", "18701265806","北京市西城区闹市口大街125号"));
            add(new AddressBean("1","张三1", "18701265806","北京市西城区闹市口大街123号"));
            add(new AddressBean("2","张三2", "18701265806","北京市西城区闹市口大街124号"));
        }
    };

    //委托投注
    public final static List<ItemBean> EntrustBetLists = new ArrayList<ItemBean>() {
        {
            add(new ItemBean("0","双色球", 5, 10));
            add(new ItemBean("1","双色球", 1, 2));
            add(new ItemBean("2","双色球", 5, 100));
        }
    };

    //委托订单 投注站
    public final static ArrayList<OrdersBean> EntrustRecordBettingLists = new ArrayList<OrdersBean>() {
        {
            add(new OrdersBean("0","2015-0612-1111", "2016/06/12 15:15", 1, "已付款", "20", Constants.EntrustBetLists));
            add(new OrdersBean("1","2015-0613-2222", "2016/06/13 15:15", 1, "已付款", "20", Constants.EntrustBetLists));
            add(new OrdersBean("2","2015-0614-3333", "2016/06/14 15:15", 1, "已付款", "20", Constants.EntrustBetLists));
        }
    };

    public final static ArrayList<OrdersBean> EntrustRecordLotteryLists = new ArrayList<OrdersBean>() {
        {
            add(new OrdersBean("0","2015-0612-1111", "2016/06/12 15:15", 3, "待出票", "20", Constants.EntrustBetLists));
            add(new OrdersBean("1","2015-0613-2222", "2016/06/13 15:15", 3, "待出票", "20", Constants.EntrustBetLists));
            add(new OrdersBean("2","2015-0614-3333", "2016/06/14 15:15", 3, "待出票", "20", Constants.EntrustBetLists));
        }
    };

    //委托投注 订单详细  双色球-0
    //复式
    public final static ArrayList<NumberBean> NumbersList1 = new ArrayList<NumberBean>(){
        {
            add(new NumberBean("0",new ArrayList<String>(){{add("3"); add("10"); add("12"); add("14"); add("16"); add("18");}},
                                   new ArrayList<String>(){{add("1"); add("3");}}));
        }
    };
    //单式
    public final static ArrayList<NumberBean> NumbersList2 = new ArrayList<NumberBean>(){
        {
            add(new NumberBean("1",new ArrayList<String>(){{add("2"); add("5"); add("7"); add("8"); add("11"); add("27");}},
                    new ArrayList<String>(){{add("3");}}));
            add(new NumberBean("2",new ArrayList<String>(){{add("2"); add("5"); add("7"); add("8"); add("11"); add("27");}},
                    new ArrayList<String>(){{add("13");}}));
        }
    };

    public final static ArrayList<NoteLotterybean> NoteLotteryLists1 = new ArrayList<NoteLotterybean>(){
        {
            add(new NoteLotterybean("0",1, 0, "双色球", 2, 4, "x2", Constants.NumbersList1.get(0)));
        }
    };
    public final static ArrayList<NoteLotterybean> NoteLotteryLists2 = new ArrayList<NoteLotterybean>(){
        {
            add(new NoteLotterybean("1",0, 0, "双色球", 1, 2, "x1", Constants.NumbersList2.get(0)));//单式
            add(new NoteLotterybean("2",0, 0, "双色球", 1, 2, "x1", Constants.NumbersList2.get(1)));
        }
    };
    public final static ArrayList<SingleTicketBean> EntrustLists = new ArrayList<SingleTicketBean>() {
        {
            add(new SingleTicketBean("0",1, "复式", 0, "双色球", 2, Constants.NoteLotteryLists1,0));
            add(new SingleTicketBean("1",0, "单式", 0, "双色球", 2, Constants.NoteLotteryLists2,1));
            add(new SingleTicketBean("2",0, "单式", 0, "双色球", 2, Constants.NoteLotteryLists2,1));
            /*add(new SingleTicketBean("3",0, "单式", 0, "双色球", 2, Constants.NoteLotteryLists2,1));
            add(new SingleTicketBean("4",0, "单式", 0, "双色球", 2, Constants.NoteLotteryLists2,1));
            add(new SingleTicketBean("5",0, "单式", 0, "双色球", 2, Constants.NoteLotteryLists2,1));*/
        }
    };

    public final static ArrayList<UserSelectNumberBean> SelectNumberLists = new ArrayList<UserSelectNumberBean>(){
        {
            add(new UserSelectNumberBean("0", 0, "双色球", "", Constants.NoteLotteryLists1));
            add(new UserSelectNumberBean("1", 0, "双色球", "", Constants.NoteLotteryLists1));
            add(new UserSelectNumberBean("2", 0, "双色球", "", Constants.NoteLotteryLists1));
        }
    };

    //管理员 配送管理
    public final static List<DeclareConsumableBean> ManagerOrderChildLists1 = new ArrayList<DeclareConsumableBean>() {
        {
            add(new DeclareConsumableBean("0", 1, "耗材申报",  0,"打印纸", "2016-01-31",  "XXXXXXXXXXXXXXXXXXX", 2));
        }
    };
    public final static List<DeclareFaultBean> ManagerOrderChildLists2 = new ArrayList<DeclareFaultBean>() {
        {
            add(new DeclareFaultBean("0",0, "故障申报",  0,"打印机", "2016-01-31", "",  "打印机维修", new BettingshopBean("0","123456", "18701265806", "西城区金融街长安兴融中心")));
        }
    };
    public final static ArrayList<DeclareOrdersBean> ManagerOrderLists = new ArrayList<DeclareOrdersBean>() {
        {
            add(new DeclareOrdersBean(0,"0","2015-0612-1111", "2016-06-12", 0, "打包备货中", "20", null, Constants.ManagerOrderChildLists1));
            add(new DeclareOrdersBean(0,"1","2015-0612-1111", "2016-06-12", 0, "打包备货中", "20", null, Constants.ManagerOrderChildLists1));

            add(new DeclareOrdersBean(0,"2","2015-0612-1111", "2016-06-12", 1, "装车中", "20", null, Constants.ManagerOrderChildLists1));
            add(new DeclareOrdersBean(0,"3","2015-0612-1111", "2016-06-12", 1, "装车中", "20", null, Constants.ManagerOrderChildLists1));

            add(new DeclareOrdersBean(0,"4","2015-0612-1111", "2016-06-12", 2, "配送中", "20", null, Constants.ManagerOrderChildLists1));
            add(new DeclareOrdersBean(0,"5","2015-0612-1111", "2016-06-12", 3, "再次配送中", "20", null, Constants.ManagerOrderChildLists1));
        }
    };
    public final static ArrayList<DeclareOrdersBean> ManagerOrderLists1 = new ArrayList<DeclareOrdersBean>() {
        {
            add(new DeclareOrdersBean(1,"0","2015-0612-1111", "2016-06-12", 0, "等待中", "0", Constants.ManagerOrderChildLists2, null));
            add(new DeclareOrdersBean(1,"1","2015-0612-1111", "2016-06-12", 0, "等待中", "0", Constants.ManagerOrderChildLists2, null));

            add(new DeclareOrdersBean(1,"2","2015-0612-1111", "2016-06-12", 1, "维修中", "20", Constants.ManagerOrderChildLists2, null));
            add(new DeclareOrdersBean(1,"3","2015-0612-1111", "2016-06-12", 1, "维修中", "20", Constants.ManagerOrderChildLists2, null));

            add(new DeclareOrdersBean(1,"4","2015-0612-1111", "2016-06-12", 2, "完成", "20", Constants.ManagerOrderChildLists2, null));
            add(new DeclareOrdersBean(1,"5","2015-0612-1111", "2016-06-12", 2, "完成", "20", Constants.ManagerOrderChildLists2, null));
        }
    };

    //银行卡数据
    public final static ArrayList<BankBean> BankLists = new ArrayList<BankBean>(){
        {
            add(new BankBean("0",0,"信用卡", "建设银行", "************4277", R.drawable.bank_nav_14, R.drawable.bank_nav_11, 1));
            add(new BankBean("1",0,"信用卡", "工商银行", "************4412", R.drawable.bank_nav_15, R.drawable.bank_nav_12, 2));
            add(new BankBean("2",0,"信用卡", "农业银行", "************4412", R.drawable.bank_nav_16, R.drawable.bank_nav_13, 3));
        }
    };

    //交易记录
    public final static ArrayList<PayRecordBean> PayRecordLists = new ArrayList<PayRecordBean>(){
        {
            add(new PayRecordBean("0",2,"提现", 0,"提现成功","96.35","2016年3月1日 15::20"));
            add(new PayRecordBean("1",1,"充值", 0,"充值成功","96.35","2016年3月1日 15::20"));
            add(new PayRecordBean("2",3,"转账", 1,"已退款","100.00","2016年3月1日 15::20"));
            add(new PayRecordBean("3",2,"提现", 0,"提现成功","96.35","2016年3月1日 15::20"));
            add(new PayRecordBean("4",1,"充值", 0,"充值成功","96.35","2016年3月1日 15::20"));
            add(new PayRecordBean("5",3,"转账", 0,"转账成功","96.35","2016年3月1日 15::20"));
        }
    };
    //交易记录筛选
    public final static ArrayList<WidgetBean1> FilterLists = new ArrayList<WidgetBean1>() {
        {
            add(new WidgetBean1("0",-1, "全部"));
            add(new WidgetBean1("1", -1, "充值"));
            add(new WidgetBean1("2", -1, "提现"));
            add(new WidgetBean1("3", -1, "转账"));
            add(new WidgetBean1("4", -1, "收款"));
            add(new WidgetBean1("5", -1, "缴费"));
            add(new WidgetBean1("6", -1, "在线支付"));
            add(new WidgetBean1("7", -1, "..."));
        }
    };

    //站点雇员列表
    public final static ArrayList<UserBean> EmployeeLists = new ArrayList<UserBean>(){
        {
            add(new UserBean("0", "", "张三1(当前在店)", "123456_1", 0, "130681198504042623", "18701265806"));
            add(new UserBean("1", "", "张三2(休息中)", "123456_2", 1, "130681198504042623", "18701265806"));
            add(new UserBean("2", "", "张三3(休息中)", "123456_3", 0, "130681198504042623", "18701265806"));
            add(new UserBean("3", "", "张三4(休息中)", "123456_4", 1, "130681198504042623", "18701265806"));
        }
    };

    //辖区列表
    public final static ArrayList<BettingshopBean> ManagerAreaLists = new ArrayList<BettingshopBean>(){
        {
            add(new BettingshopBean("0", "", "123456", "", "张三11", "18701265806", "北京市西城区长安兴融中心", "1,000"));
            add(new BettingshopBean("1", "", "123456", "", "李四11",  "18701265806", "北京市西城区长安兴融中心", "1,000"));
            add(new BettingshopBean("2", "", "123456", "", "张三22", "18701265806", "北京市西城区长安兴融中心", "1,000"));
            add(new BettingshopBean("3", "", "123456", "", "李四22",  "18701265806", "北京市西城区长安兴融中心", "1,000"));
        }
    };

    /****************************************************聊天***********************************************/
    public final static ArrayList<WidgetBean1> ContactMenuLists = new ArrayList<WidgetBean1>(){
        {
            add(new WidgetBean1("0",R.mipmap.chat_icon4,"通讯录"));
            add(new WidgetBean1("1",R.mipmap.chat_icon3,"添加好友"));
            add(new WidgetBean1("2",R.mipmap.chat_icon2,"发起群聊"));
        }
    };

    public final static ArrayList<ChatMessageBean> messageLists = new ArrayList<ChatMessageBean>(){
        {
            add(new ChatMessageBean("0","下午 5:15","你好，请问在吗？"));
            add(new ChatMessageBean("1","下午 4:15","你好，请问在吗？"));
            add(new ChatMessageBean("2","下午 3:15","你好，请问在吗？"));
        }
    };

    //私聊中的消息列表
    public final static ArrayList<ChatBean> chatLists = new ArrayList<ChatBean>() {
        {
            add(new ChatBean("0","http://www.feizl.com/upload2007/2014_06/1406272351394618.png","张三", "", messageLists, 0));//未读
            add(new ChatBean("1","http://img.wzfzl.cn/uploads/allimg/140820/co140R00Q925-14.jpg","李四", "", messageLists, 1));
        }
    };
    /****************************************************聊天***********************************************/
}
