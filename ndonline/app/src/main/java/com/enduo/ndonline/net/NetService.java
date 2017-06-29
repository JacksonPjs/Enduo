package com.enduo.ndonline.net;


import android.support.annotation.RequiresApi;

import com.enduo.ndonline.appupdate.AppUpdataBean;
import com.enduo.ndonline.bean.AconntBean;
import com.enduo.ndonline.bean.AnnouncementBean;
import com.enduo.ndonline.bean.BankListBean;
import com.enduo.ndonline.bean.BiaoBean;
import com.enduo.ndonline.bean.BorrowDetailBean;
import com.enduo.ndonline.bean.CaseFlowBean;
import com.enduo.ndonline.bean.CenterIndexBean;
import com.enduo.ndonline.bean.CertificationBean;
import com.enduo.ndonline.bean.ChagerBean;
import com.enduo.ndonline.bean.DiscountBean;
import com.enduo.ndonline.bean.DiscountListBean;
import com.enduo.ndonline.bean.InfoBean;
import com.enduo.ndonline.bean.InterestBean;
import com.enduo.ndonline.bean.IntroduceBean;
import com.enduo.ndonline.bean.InvestmentBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.bean.MyMessgeBean;
import com.enduo.ndonline.bean.OneBean;
import com.enduo.ndonline.bean.ProductDetialBean;
import com.enduo.ndonline.bean.RegisterBean;
import com.enduo.ndonline.bean.ShareBean;
import com.enduo.ndonline.bean.TouziBean;
import com.enduo.ndonline.bean.WithdrawBean;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by maxy on 2016/11/22.
 */
public interface NetService {
    //服务器路径
//    public static final String API_SERVER = "http://172.18.5.252:8080/jp/app/";//测试地址


    public static final String API_SERVER = "http://www.enduo168.com/app/";  //上线地址
    //网址路径
    public static final String API_SERVER_Url = "http://192.168.1.196:8080/jp/";


//    public static final String API_SERVER_Url = "http://www.enduo168.com/";

    //主程序地址
    public static final String API_SERVER_Main = API_SERVER_Url;
    //测试主程序地址
//    public static final String API_SERVER_Main = "http://172.18.5.252:8080/jp/";
    //图片地址
    // public static final String API_SERVER_Photo = "http://192.168.1.196:8080/";


    @POST("selectUserNumber.html")
    Observable<AconntBean> selectUserNumber();

    //提现
    @POST("userWithdraw.html")
    Observable<WithdrawBean> userWithdraw();


    // 开启滑块
    @POST("startCaptcha.html")
    Observable<String> startCaptcha(@Query("noncestr") String noncestr);


    // 取短信验证码
    @POST("getPhoneCode.html")
    Observable<String> getPhoneCode(@Query("noncestr") String noncestr, @Query("cellPhone") String cellPhone);

    /**
     * 提现申请
     *
     * @return
     */
    @POST("tongLianUserWithdraw.html")
    Observable<InfoBean> tongLianUserWithdraw(@Query("withdrawAmount") String withdrawAmount,
                                              @Query("pwd") String pwd,@Query("bankcode") String bankCardNo,@Query("bankCardId") String bankCardId);

    @POST("selectBorrowList.html")
    Observable<BiaoBean> selectBorrowList(@Query("curPage") String curPage, @Query("pageSize") String pageSize);


    /**
     * 获取产品列表
     */
    @POST("selectBorrowListApp.html")
    Observable<BiaoBean> selectBorrowListApp(@Query("curPage") String curPage, @Query("pageSize") String pageSize,@Query("borrowType") String borrowType);

    /**
     * 新手标
     */
    @POST("selectNoviceBorrowList.html")
    Observable<BiaoBean> selectNoviceBorrowList(@Query("curPage") String curPage, @Query("pageSize") String pageSize);


    /**
     * 推荐人列表
     */
    @POST("selectReferee.html")
    Observable<ShareBean> selectReferee(@Query("curPage") String curPage, @Query("pageSize") String pageSize);

    /**
     * 充值
     */
    @POST("wxpay.html")
    Observable<ChagerBean> wxpay(@Query("payway") String payway, @Query("rechargeAmount") String rechargeAmount);

    /**
     * 21． 首页
     */
    @POST("index.html")
    Observable<OneBean> index();

    /**
     * 个人中心
     */
    @POST("selectUserIndex.html")
    Observable<CenterIndexBean> selectUserIndex();

    /**
     * 版本升级
     */
    @POST("appCurrentVersion.html")
    Observable<AppUpdataBean> appCurrentVersion(@Query("phoneType") String phoneType);

    /**
     * 6.实名验证
     */
    @POST("regPerson.html")
    Observable<InfoBean> regPerson(@Query("realName") String realName, @Query("cardNo") String cardNo);

    /**
     * 修改登录密码
     */
    @POST("updateUserPass.html")
    Observable<InfoBean> updateUserPass(@Query("oldPass") String oldPass, @Query("newPass") String newPass);

    /**
     * 6.设置交易密码
     */
    @POST("setUserPayPwd.html")
    Observable<InfoBean> setUserPayPwd(@Query("address") String address, @Query("reAddress") String reAddress);


    /**
     * 找回密码 验证手机号码
     */
    @POST("forGetPassPhone.html")
    Observable<InfoBean> forGetPassPhone(@Query("param") String param);


    /**
     * 投标
     */
    @POST("bfpay/investAjaxBorrow.html")
    Observable<String> investAjaxBorrow(@Query("tradingPassword") String tradingPassword, @Query("investAmount") String investAmount, @Query("borrowId") String borrowId);


    /**
     * 6.实名验证
     */
    @POST("userPerson.html")
    Observable<CertificationBean> userPerson();

    /**
     * 产品详情
     */
    @POST("queryBorrowDetail.html")
    Observable<BorrowDetailBean> queryBorrowDetail(@Query("id") String id);


    /**
     * 4.验证推荐人是否正确
     */
    @POST("chackRefereeUser.html")
    Observable<String> chackRefereeUser(@Query("regReferee") String regReferee);

    /**
     * 4.注册
     */
    @POST("regist.html")
    Observable<InfoBean> regist(@Query("cellPhone") String cellPhone, @Query("pwd") String pwd, @Query("regCode") String regCode, @Query("regReferee") String regReferee);

    /**
     * 找回密码
     */
    @POST("updateforGetPass.html")
    Observable<InfoBean> updateforGetPass(@Query("phone") String phone, @Query("telCode") String telCode, @Query("forGetpassword") String forGetpassword);

    /**
     * 修改支付密码
     */
    @POST("updateUserPay.html")
    Observable<InfoBean> updateUserPay(@Query("phone") String phone, @Query("telCode") String telCode, @Query("newPass") String newPass);

    /**
     * /**
     * 1,登录
     */
    @POST("login.html")
    Observable<LoginBean> login(@Query("userName") String userName, @Query("pwd") String pwd);

    /**
     * 我的投资
     */
    @POST("selectInvestListing.html")
    Observable<TouziBean>  selectInvestListing(@Query("curPage") String curPage, @Query("pageSize") String pageSize);

    /**
     * 我的消息
     */
    @POST("selectUserMessage.html")
    Observable<MyMessgeBean> selectUserMessage(@Query("curPage") String curPage, @Query("pageSize") String pageSize);

    /**
     * 我的消息--- 详情
     */
    @POST("showUserMessage.html")
    Observable<String> showUserMessage(@Query("messageId") String messageId);

    /**
     * 资金流水
     */
    @POST("moneyFlow.html")
    Observable<CaseFlowBean> moneyFlow(@Query("curPage") String curPage, @Query("pageSize") String pageSize);

    /**
     * 产品介绍
     */
    @POST("queryBorrowIntroduce.html")
    Observable<IntroduceBean> queryBorrowIntroduce(@Query("id") String id);

    /**
     * 产品资质资料
     */
    @POST("queryBorrowData.html")
    Observable<ProductDetialBean> queryBorrowData(@Query("id") String id);

    /**
     * 产品风控意见
     */
    @POST("queryBorrowRisk.html")
    Observable<IntroduceBean> queryBorrowRisk(@Query("id") String id);

    /**
     * 2.验证手机号码是否存在
     */
    @POST("verificationNewUserPhone.html")
    Observable<InfoBean> verificationNewUserPhone(@Query("userPhone") String userPhone);

    /**
     * 银行卡列表
     */
    @POST("selectBankCard.html")
    Observable<BankListBean> selectBankCard();

    /**
     * 银行卡解绑
     */
    @POST("deleteBankCard.html")
    Observable<InfoBean> deleteBankCard(@Query("id") String id);

    /**
     * 产品投资记录
     */
    @POST("borrowInvestList.html")
    Observable<InvestmentBean> borrowInvestList(@Query("borrowId") String borrowId,@Query("result") String result,
                                                @Query("curPage") String curPage, @Query("pageSize") String pageSize);


    /**
     * 平台公告
     */
    @POST("noticeList.html")
    Observable<AnnouncementBean> noticeList(@Query("curPage") String curPage, @Query("pageSize") String pageSize);

    /*
    * 感兴趣
    */
    @POST("consultationPageApp.html")
    Observable<InterestBean> interestList(@Query("curPage") String curPage, @Query("pageSize") String pageSize); /*
    /* 理财详情代金券列表
    */
    @POST("queryUserCoupon.html")
    Observable<DiscountBean> interestDisountList(@Query("Cookie") String Cookie); /* 理财详情代金券列表
   /* 投标
    */
    @POST("bfpay/investAjaxBorrow.html")
    Observable<DiscountBean> investAjaxBorrow(@Query("Cookie") String Cookie,@Query("tradingPassword") String tradingPassword,@Query("investAmount") String investAmount,@Query("borrowId") String borrowId,@Query("couponId") String couponId);
    /* 优惠券列表
        */
    @POST("queryTCouponAndJxList.html")
    Observable<DiscountListBean> CouponAndJxList(@Query("Cookie") String Cookie, @Query("couponStatus") String tradingPassword
                                             , @Query("curPage") String curPage, @Query("pageSize") String pageSize);
}
