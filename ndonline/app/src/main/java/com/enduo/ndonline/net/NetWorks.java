package com.enduo.ndonline.net;


import com.enduo.ndonline.MyApplication;
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

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by maxy on 2016/11/22.
 */
public class NetWorks extends RetrofitUtils {
    protected static final NetService service = getRetrofit().create(NetService.class);


    /**
     * 获取理财列表
     *
     * @param observer
     */
    public static void selectBorrowList(String page, String pagesize, Subscriber<BiaoBean> observer) {
        setSubscribe(service.selectBorrowList(page, pagesize), observer);
    }

    /**
     * 获取理财列表(新)
     *
     * @param observer
     */
    public static void selectBorrowListApp(String page, String pagesize,String type, Subscriber<BiaoBean> observer) {
        setSubscribe(service.selectBorrowListApp(page, pagesize,type), observer);
    }

    /**
     * 推荐人列表
     *
     * @param observer
     */
    public static void selectReferee(String page, String pagesize, Subscriber<ShareBean> observer) {
        setSubscribe(service.selectReferee(page, pagesize), observer);
    }

    /**
     * 提现
     *
     * @param observer
     */
    public static void tongLianUserWithdraw(String withdrawAmount, String pwd,String bankcode,String cardId,Subscriber<InfoBean> observer) {
        setSubscribe(service.tongLianUserWithdraw(withdrawAmount, pwd,bankcode,cardId), observer);
    }

    /**
     * 充值
     *
     * @param observer
     */
    public static void wxpay(String payway, String rechargeAmount, Subscriber<ChagerBean> observer) {
        setSubscribe(service.wxpay(payway, rechargeAmount), observer);
    }

    /**
     * 获取理财列表
     *
     * @param observer
     */
    public static void userWithdraw(Subscriber<WithdrawBean> observer) {
        setSubscribe(service.userWithdraw(), observer);
    }

    /**
     * 版本更新
     *
     * @param observer
     */
    public static void appCurrentVersion(Subscriber<AppUpdataBean> observer) {
        setSubscribe(service.appCurrentVersion("1"), observer);
    }

    /**
     * 获取新手标
     *
     * @param observer
     */
    public static void selectNoviceBorrowList(String page, String pagesize, Subscriber<BiaoBean> observer) {
        setSubscribe(service.selectNoviceBorrowList(page, pagesize), observer);
    }

    /**
     * 设置交易密码
     *
     * @param observer
     */
    public static void setUserPayPwd(String address, String readdress, Subscriber<InfoBean> observer) {
        setSubscribe(service.setUserPayPwd(address, readdress), observer);
    }

    /**
     * 投资
     *
     * @param observer
     */
    public static void investAjaxBorrow(String tradingPassword, String investAmount, String borrowId, Subscriber<String> observer) {
        setSubscribe(service.investAjaxBorrow(tradingPassword, investAmount, borrowId), observer);
    }

    /**
     * 2.账户总览
     *
     * @param observer
     */
    public static void selectUserNumber(Subscriber<AconntBean> observer) {
        setSubscribe(service.selectUserNumber(), observer);
    }

    /**
     * 个人中心
     *
     * @param observer
     */
    public static void selectUserIndex(Subscriber<CenterIndexBean> observer) {
        setSubscribe(service.selectUserIndex(), observer);
    }

    /**
     * 投资记录
     *
     * @param observer
     */
    public static void borrowInvestList(String id,String result, String page, String pagesize, Subscriber<InvestmentBean> observer) {
        setSubscribe(service.borrowInvestList(id,result, page, pagesize), observer);
    }

    /**
     * 平台公告
     *
     * @param observer
     */
    public static void noticeList(String page, String pagesize, Subscriber<AnnouncementBean> observer) {
        setSubscribe(service.noticeList(page, pagesize), observer);
    }
/*
* 感兴趣*/
    public static void interestList(String page, String pagesize, Subscriber<InterestBean> observer){
        setSubscribe(service.interestList(page, pagesize), observer);
    }
    /*
    * 理财详情代金券列表*/
    public static void interestDisountList(String page ,Subscriber<DiscountBean> observer){
        setSubscribe(service.interestDisountList(page), observer);
    }
    /**
     * 修改登录密码
     *
     * @param observer
     */
    public static void updateUserPass(String oldPass, String newPass, Subscriber<InfoBean> observer) {
        setSubscribe(service.updateUserPass(oldPass, newPass), observer);
    }

    /**
     * 实名验证
     *
     * @param observer
     */
    public static void regPerson(String realName, String no, Subscriber<InfoBean> observer) {
        setSubscribe(service.regPerson(realName, no), observer);
    }

    /**
     * 获取实名信息
     *
     * @param observer
     */
    public static void userPerson(Subscriber<CertificationBean> observer) {
        setSubscribe(service.userPerson(), observer);
    }

    /**
     * 获取实名信息
     *
     * @param observer
     */
    public static void selectBankCard(Subscriber<BankListBean> observer) {
        setSubscribe(service.selectBankCard(), observer);
    }

    /**
     * 开启验证码
     *
     * @param observer
     */
    public static void startCaptcha(String noncestr ,Subscriber<String> observer) {
        setSubscribe(service.startCaptcha(noncestr), observer);
    }


    /**
     * 取短信验证码
     *
     * @param observer
     */
    public static void getPhoneCode(String noncestr ,String cellPhone ,Subscriber<String> observer) {
        setSubscribe(service.getPhoneCode(noncestr,cellPhone), observer);
    }

    /**
     * 银行卡解绑
     *
     * @param observer
     */
    public static void deleteBankCard(String id, Subscriber<InfoBean> observer) {
        setSubscribe(service.deleteBankCard(id), observer);
    }

    /**
     * 获取新手标
     *
     * @param observer
     */
    public static void queryBorrowDetail(String id, Subscriber<BorrowDetailBean> observer) {
        setSubscribe(service.queryBorrowDetail(id), observer);
    }

    /**
     * 产品介绍
     *
     * @param observer
     */
    public static void queryBorrowIntroduce(String id, Subscriber<IntroduceBean> observer) {
        setSubscribe(service.queryBorrowIntroduce(id), observer);
    }

    /**
     * 风控师意见
     *
     * @param observer
     */
    public static void queryBorrowRisk(String id, Subscriber<IntroduceBean> observer) {
        setSubscribe(service.queryBorrowRisk(id), observer);
    }

    /**
     * 首页
     *
     * @param observer
     */
    public static void index(Subscriber<OneBean> observer) {
        setSubscribe(service.index(), observer);
    }

    /**
     * 风控师意见
     *
     * @param observer
     */
    public static void queryBorrowData(String id, Subscriber<ProductDetialBean> observer) {
        setSubscribe(service.queryBorrowData(id), observer);
    }

    /**
     * 2.验证手机号码是否存在
     *
     * @param observer
     */
    public static void verificationNewUserPhone(String userPhone, Subscriber<InfoBean> observer) {
        setSubscribe(service.verificationNewUserPhone(userPhone), observer);
    }

    /**
     * 2.验证手机号码是否存在
     *
     * @param observer
     */
    public static void chackRefereeUser(String regReferee, Subscriber<String> observer) {
        setSubscribe(service.chackRefereeUser(regReferee), observer);
    }

    /**
     * 2.验证手机号码是否存在
     *
     * @param observer
     */
    public static void forGetPassPhone(String param, Subscriber<InfoBean> observer) {
        setSubscribe(service.forGetPassPhone(param), observer);
    }

    /**
     * 注册
     *
     * @param observer
     */
    public static void regist(String cellPhone, String pwd, String regCode, String regReferee, Subscriber<InfoBean> observer) {
        setSubscribe(service.regist(cellPhone, pwd, regCode, regReferee), observer);
    }

    /**
     * 忘记密码
     *
     * @param observer
     */
    public static void updateforGetPass(String phone, String telCode, String forGetpassword, Subscriber<InfoBean> observer) {
        setSubscribe(service.updateforGetPass(phone, telCode, forGetpassword), observer);
    }

    /**
     * 修改支付密码
     *
     * @param observer
     */
    public static void updateUserPay(String phone, String telCode, String newPass, Subscriber<InfoBean> observer) {
        setSubscribe(service.updateUserPay(phone, telCode, newPass), observer);
    }

    /**
     * 我的投资
     *
     * @param observer
     */
    public static void selectInvestListing(String curPage, String pageSize, Subscriber<TouziBean> observer) {
        setSubscribe(service.selectInvestListing(curPage, pageSize), observer);
    }

    /**
     * 我的消息
     *
     * @param observer
     */
    public static void selectUserMessage(String curPage, String pageSize, Subscriber<MyMessgeBean> observer) {
        setSubscribe(service.selectUserMessage(curPage, pageSize), observer);
    }

    /**
     * 我的投资
     *
     * @param observer
     */
    public static void moneyFlow(String curPage, String pageSize, Subscriber<CaseFlowBean> observer) {
        setSubscribe(service.moneyFlow(curPage, pageSize), observer);
    }

    /**
     * 我的消息 ---  详情
     *
     * @param observer
     */
    public static void showUserMessage(String messageId, Subscriber<String> observer) {
        setSubscribe(service.showUserMessage(messageId), observer);
    }

    /**
     * 登录
     *
     * @param observer
     */
    public static void login(String cellPhone, String pwd, Subscriber<LoginBean> observer) {
        setSubscribe(service.login(cellPhone, pwd), observer);
    }

    /**
     * 投标
     *
     * @param observer
     */
    public static void investAjaxBorrow(String Cookie, String pwd, String investAmount, String borrowId, String couponId, Subscriber<DiscountBean> observer) {
        setSubscribe(service.investAjaxBorrow(Cookie, pwd,investAmount,borrowId,couponId), observer);
    }

    /**
     *优惠券列表
     *
     * @param observer
     */
    public static void CouponAndJxList(String Cookie, String pwd ,String curPage, String pageSize, Subscriber<DiscountListBean> observer) {
        setSubscribe(service.CouponAndJxList(Cookie, pwd,curPage,pageSize),observer);
    }

//    public static void  getImgList(String page,Subscriber<String> observer){
//        setSubscribe(service.getImgList(page,"10",( System.currentTimeMillis()+"").substring(0,10),key,"desc"),observer);
//    }


    /**
     * 插入观察者-泛型
     *
     * @param observable
     * @param observer
     * @param <T>
     */
    public static <T> void setSubscribe(Observable<T> observable, Subscriber<T> observer) {

        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
