package com.nanruan.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderIndex {

    private Long Index_ID;//	bigint
    private String  Index_GUID;//	nvarchar	随机值
    private int opt_status;//	int
    private String  Index_Code;//	nvarchar	单据编号
    private String  Index_PactCode;//	nvarchar	合同编号
    private Long  Index_EndUserID;//	bigint	收货人编号(ID)
    private String  Index_EndUserName;//	nvarchar	收货人名称
    private Long Index_FromManID;//	bigint	发货联系人ID
    private String  Index_FromMan;//	nvarchar	发货联系人
    private String  Index_From;//	nvarchar	发货地址
    private Long  Index_FromProvince;//	bigint	出发省
    private Long Index_FromCity;//	bigint	出发市
    private Long Index_FromDistrict;//	bigint	出发区
    private Date Index_FromTime;//	datetime	出发时间
    private Long Index_ToManID;//	bigint	收货联系人ID
    private String  Index_ToMan;//	nvarchar	收货联系人
    private String   Index_To;//	nvarchar	收货地址
    private Long  Index_ToProvince;//	bigint	到达省
    private Long  Index_ToCity;//	bigint	到达市
    private Long Index_ToDistrict;//	bigint	到达区
    private String  Index_ToTime;//	到达时间
    private Long  Index_TransportMode;	//bigint	运输模式：1 零担 2 整车 3 空运 4 快递 5 铁路 6 海运
    private Long  Index_GoodsCategory;	//bigint	货物类型：1 普通货物 2 危险品 4 温控获取，允许使用or
    private Long  Index_PackageMode;	//bigint	包装类型：1 散箱(可堆叠，人工装卸) 2 托盘或木箱(可堆叠，叉车装卸) 3 托盘、木箱或不规则形状，不可堆叠，叉车装卸
    private Long  Index_ChargeMode;	//bigint	计价方式：1 体积 2 重量
    private Long  Index_PriceUnit;	//bigint	计价单位：1 公斤2 吨 3 立方 4 升 5 其他
    private Long  Index_Status;	//bigint	订单状态,-1草稿,0新单据,1待调度, 2待签收,4待回单,8待结账,16已结账,32已关闭
    private Date Index_StatusTime;	//datetime	订单状态修改时间
    private Long Index_SrcOrderID;	//bigint	来源订单
    private Long Index_RootOrderID	;//bigint	起始订单
    private Long Index_SrcClass;	//bigint	订单类型，1 客户订单 2 运输订单 3 拼车订单
    private Long Index_Kms;	//bigint	公里数
    private Long Index_CarType;	//bigint	车型
    private double Index_CarLength;	//decimal	车长
    private Long Index_DriverID;	//bigint	司机编号
    private Long Index_CarID;	//bigint	车辆编号
    private Long Index_SupplierID;	//bigint	供应商编号
    private Long Index_SupplierCompanyID;	//bigint	供应商公司编号
    private Long Index_CustomerID;	//bigint	客户编号
    private Long Index_CustomerCompanyID;	//bigint	客户公司编号
    private Long Index_ShipMode;	//bigint	运输模式, 1 市内 2 长途
    private Long Index_Pick;	//bigint	是否提货
    private Long Index_Delivery;	//bigint	是否送货
    private Long Index_Creator;	//bigint	下单人
    private Long Index_CreatorCompanyID;	//bigint	下单人所属公司
    private Date Index_CreateTime;	//datetime	下单时间
    private Long Index_Confirmer;	//bigint	审核(或接受)人
    private Date Index_ConfirmTime;	//datetime	审核(或接收)时间
    private Long Index_Singer;	//bigint	签收人
    private Date Index_SignTime;	//datetime	签收时间
    private  String Index_ReceiptDoc;	//nvarchar	签收单据图片路径
    private  String  Index_Exception;	//nvarchar	异常描述
    private  int Index_Invalid;	//tinyint	是否有效
    private  String  Index_Comments;	//varchar	备注
    private Long Index_OnLoad;	//bigint	装货
    private Long Index_OffLoad;	//bigint	卸货
    private Long Index_Insurance;	//bigint	保价
    private BigDecimal Index_VolumeAddition;	//decimal	体积补差
    private BigDecimal Index_WeightAddition;	//decimal	重量补差
    private String Index_Description;	//nvarchar	描叙
    private BigDecimal Index_CarVolume;	//decimal	车体积
    private BigDecimal Index_CarWeight;	//decimal	车重量
    private Long Index_Combined;	//bigint	是否拼车
    private Long Index_CustomerSymbolID;	//bigint	线下客户
    private Long Index_SupplierSymbolID;	//bigint	线下承运商
    private String Index_ReceiptDoc1;	//nvarchar
    private String Index_ReceiptDoc2;	//nvarchar
    private String  Index_ReceiptDoc3;	//nvarchar
    private String Index_FromContact;	//nvarchar	发货联系电话
    private String Index_ToContact;	//nvarchar	收货联系电话
    private BigDecimal Index_GoodsValue;	//decimal	物品价值
    private Long Index_PrevOrderID;	//bigint	拆单ID
    private String Index_DeviceCode;	//nvarchar	设备号码
    private BigDecimal Index_Payment;	//decimal	预付
    private BigDecimal Index_Payable;	//decimal	到付
    private String Index_GoodsLst; //	nvarchar	物品列表
    private Date Index_RealFromTime; //	datetime	实际出发时间
    private Date Index_RealToTime;	//datetime	实际到达时间
    private String Index_ReceiptDoc4;	//nvarchar
    private String Index_ReceiptDoc5;	//nvarchar
    private String Index_ReceiptDoc6;	//nvarchar
    private String Index_ReceiptDoc7;	//nvarchar
    private int Index_RollBack;	//tinyint	撤回
    private Date Index_ReceipTime;	//datetime	回单时间
    private String Index_FromOperator;	//nvarchar	对接操作人
    private Long Index_TerminalOrderID;	//bigint	对接ID
    private String Index_TerminalOrderCode;	//nvarchar	对接编号
    private String Index_CustomerName	;//nvarchar	客户名字
    private String Index_SupplierName;	//nvarchar	承运商名字
    private String Index_CreatorCompanyName;	//nvarchar	所属公司
    private String Index_CustomerSymbolName;	//nvarchar	线下客户名字
    private String Index_SupplierSymbolName;	//nvarchar	线下承运商
    private Long Index_TotalCost;	//decimal	运费总金额
    private Long  Index_TotalAmount;	//decimal	总数量
    private Long  Index_TotalWeight;	//decimal	总重量
    private Long  Index_TotalVolume;	//decimal	总体积
    private Long Index_CombinedAmount;	//decimal	拼车物品数量
    private Long  Index_CombinedCost;	//decimal	拼车总费用
    private Long Index_CombinedWeight;	//decimal	拼车体积
    private Long Index_CombinedVolume;	//decimal	拼车重量
    private Long Index_CombinedOrderAmount;	//bigint	拼车订单数
    private String  Index_CombinedFrom;	//nvarchar	拼车出发地
    private String  Index_CombinedTo;	//nvarchar	拼车目的地
    private int Index_TCacheReady;	//tinyint	运输缓存已就绪
    private Date  Index_StartMsgTime;	//datetime	出发短信时间
    private Date Index_ArriveMsgTime;	//datetime	预计达到时间
    private String Index_VerifyCode;	//varchar	电子回单验证码
    private Date Index_DeviceCreatTime;	//datetime	设备绑定时间
    private Date Index_GPSStartTime;	//datetime	GPS开始时间
    private Date Index_DeviceBindingTime;	//datetime	设备绑定时间
    private String  Index_FromLocation;	//varchar	出发坐标
    private String Index_ToLocation;	//varchar	到达坐标
    private Date  Index_ContainsBillDay;	//datetime	拼车单计算关账日时间
    private int Index_TrackType;	//tinyint	设备类型 1 电子回单 2 快递
    private int Index_SupplierType;	//tinyint	承运商类型 1 线上 2 线下 3 自营
    private int Index_CusRollBack;	//tinyint	撤销订单
    private int Index_Fromtype;	//tinyint	出发地:1 精确坐标 0 大概坐标
    private int Index_Totype;	//tinyint	目的地 1 精确坐标 0 大概坐标
    private Long  Index_RollBackOrderID;	//bigint	撤销订单ID
    private String Index_tpPrint;	//varchar	脱普辅助物打印(什么叫辅助物不知道)
    private int Index_ExceptionType; //	tinyint	异常签收情况分类 0 无异常 1 缺少 2 丢失 4 破损 8 其他（作废）
    private int Index_AssessLevel;	//tinyint	订单打分满分5分
    private String Index_AssessMent;	//nvarchar	打分说明
    private int  Index_CloseMark; //	int	关单注记 0 无关单 -- 1 客服发起关单,2 运营发起关单,3 客服关闭申请,4 运营关闭通知,5 客服关单通知,6 拒绝关单,7 客服已关单,8 运营关单 9 关单等待
    private String Index_CloseMent;	//nvarchar	关单备注
    private  int Index_Addorder;	//tinyint	是否带单 0 否 1是
    private String Index_AddPacth;	//nvarchar	附属主单合同号
    private int Index_CarCount;	//int	整车车数 默认1
    private int Index_AutoSchedule;	//tinyint	是否自动调度 0 否 1 是
    private int Index_CombinePrice;	//tinyint	拼车是否匹配合约
    private int Index_OrderType;	//tinyint	订单关系 0 普通单 1 主单 2 从单
    private BigDecimal Index_ExceptionCost;	//decimal	异常费
    private int Index_BeSplit;	//tinyint	是否拆单、拆单类型
    private int Index_SplitType	;//tinyint	1 线路拆单 2 数量拆单
    private int Index_signinfotype;  //	tinyint	签收扩展类型
    private String Index_signinfo ; //nvarchar	签收扩展信息

    public Long getIndex_ID() {
        return Index_ID;
    }

    public void setIndex_ID(Long index_ID) {
        Index_ID = index_ID;
    }

    public String getIndex_GUID() {
        return Index_GUID;
    }

    public void setIndex_GUID(String index_GUID) {
        Index_GUID = index_GUID;
    }

    public int getOpt_status() {
        return opt_status;
    }

    public void setOpt_status(int opt_status) {
        this.opt_status = opt_status;
    }

    public String getIndex_Code() {
        return Index_Code;
    }

    public void setIndex_Code(String index_Code) {
        Index_Code = index_Code;
    }

    public String getIndex_PactCode() {
        return Index_PactCode;
    }

    public void setIndex_PactCode(String index_PactCode) {
        Index_PactCode = index_PactCode;
    }

    public Long getIndex_EndUserID() {
        return Index_EndUserID;
    }

    public void setIndex_EndUserID(Long index_EndUserID) {
        Index_EndUserID = index_EndUserID;
    }

    public String getIndex_EndUserName() {
        return Index_EndUserName;
    }

    public void setIndex_EndUserName(String index_EndUserName) {
        Index_EndUserName = index_EndUserName;
    }

    public Long getIndex_FromManID() {
        return Index_FromManID;
    }

    public void setIndex_FromManID(Long index_FromManID) {
        Index_FromManID = index_FromManID;
    }

    public String getIndex_FromMan() {
        return Index_FromMan;
    }

    public void setIndex_FromMan(String index_FromMan) {
        Index_FromMan = index_FromMan;
    }

    public String getIndex_From() {
        return Index_From;
    }

    public void setIndex_From(String index_From) {
        Index_From = index_From;
    }

    public Long getIndex_FromProvince() {
        return Index_FromProvince;
    }

    public void setIndex_FromProvince(Long index_FromProvince) {
        Index_FromProvince = index_FromProvince;
    }

    public Long getIndex_FromCity() {
        return Index_FromCity;
    }

    public void setIndex_FromCity(Long index_FromCity) {
        Index_FromCity = index_FromCity;
    }

    public Long getIndex_FromDistrict() {
        return Index_FromDistrict;
    }

    public void setIndex_FromDistrict(Long index_FromDistrict) {
        Index_FromDistrict = index_FromDistrict;
    }

    public Date getIndex_FromTime() {
        return Index_FromTime;
    }

    public void setIndex_FromTime(Date index_FromTime) {
        Index_FromTime = index_FromTime;
    }

    public Long getIndex_ToManID() {
        return Index_ToManID;
    }

    public void setIndex_ToManID(Long index_ToManID) {
        Index_ToManID = index_ToManID;
    }

    public String getIndex_ToMan() {
        return Index_ToMan;
    }

    public void setIndex_ToMan(String index_ToMan) {
        Index_ToMan = index_ToMan;
    }

    public String getIndex_To() {
        return Index_To;
    }

    public void setIndex_To(String index_To) {
        Index_To = index_To;
    }

    public Long getIndex_ToProvince() {
        return Index_ToProvince;
    }

    public void setIndex_ToProvince(Long index_ToProvince) {
        Index_ToProvince = index_ToProvince;
    }

    public Long getIndex_ToCity() {
        return Index_ToCity;
    }

    public void setIndex_ToCity(Long index_ToCity) {
        Index_ToCity = index_ToCity;
    }

    public Long getIndex_ToDistrict() {
        return Index_ToDistrict;
    }

    public void setIndex_ToDistrict(Long index_ToDistrict) {
        Index_ToDistrict = index_ToDistrict;
    }

    public String getIndex_ToTime() {
        return Index_ToTime;
    }

    public void setIndex_ToTime(String index_ToTime) {
        Index_ToTime = index_ToTime;
    }

    public Long getIndex_TransportMode() {
        return Index_TransportMode;
    }

    public void setIndex_TransportMode(Long index_TransportMode) {
        Index_TransportMode = index_TransportMode;
    }

    public Long getIndex_GoodsCategory() {
        return Index_GoodsCategory;
    }

    public void setIndex_GoodsCategory(Long index_GoodsCategory) {
        Index_GoodsCategory = index_GoodsCategory;
    }

    public Long getIndex_PackageMode() {
        return Index_PackageMode;
    }

    public void setIndex_PackageMode(Long index_PackageMode) {
        Index_PackageMode = index_PackageMode;
    }

    public Long getIndex_ChargeMode() {
        return Index_ChargeMode;
    }

    public void setIndex_ChargeMode(Long index_ChargeMode) {
        Index_ChargeMode = index_ChargeMode;
    }

    public Long getIndex_PriceUnit() {
        return Index_PriceUnit;
    }

    public void setIndex_PriceUnit(Long index_PriceUnit) {
        Index_PriceUnit = index_PriceUnit;
    }

    public Long getIndex_Status() {
        return Index_Status;
    }

    public void setIndex_Status(Long index_Status) {
        Index_Status = index_Status;
    }

    public Date getIndex_StatusTime() {
        return Index_StatusTime;
    }

    public void setIndex_StatusTime(Date index_StatusTime) {
        Index_StatusTime = index_StatusTime;
    }

    public Long getIndex_SrcOrderID() {
        return Index_SrcOrderID;
    }

    public void setIndex_SrcOrderID(Long index_SrcOrderID) {
        Index_SrcOrderID = index_SrcOrderID;
    }

    public Long getIndex_RootOrderID() {
        return Index_RootOrderID;
    }

    public void setIndex_RootOrderID(Long index_RootOrderID) {
        Index_RootOrderID = index_RootOrderID;
    }

    public Long getIndex_SrcClass() {
        return Index_SrcClass;
    }

    public void setIndex_SrcClass(Long index_SrcClass) {
        Index_SrcClass = index_SrcClass;
    }

    public Long getIndex_Kms() {
        return Index_Kms;
    }

    public void setIndex_Kms(Long index_Kms) {
        Index_Kms = index_Kms;
    }

    public Long getIndex_CarType() {
        return Index_CarType;
    }

    public void setIndex_CarType(Long index_CarType) {
        Index_CarType = index_CarType;
    }

    public double getIndex_CarLength() {
        return Index_CarLength;
    }

    public void setIndex_CarLength(double index_CarLength) {
        Index_CarLength = index_CarLength;
    }

    public Long getIndex_DriverID() {
        return Index_DriverID;
    }

    public void setIndex_DriverID(Long index_DriverID) {
        Index_DriverID = index_DriverID;
    }

    public Long getIndex_CarID() {
        return Index_CarID;
    }

    public void setIndex_CarID(Long index_CarID) {
        Index_CarID = index_CarID;
    }

    public Long getIndex_SupplierID() {
        return Index_SupplierID;
    }

    public void setIndex_SupplierID(Long index_SupplierID) {
        Index_SupplierID = index_SupplierID;
    }

    public Long getIndex_SupplierCompanyID() {
        return Index_SupplierCompanyID;
    }

    public void setIndex_SupplierCompanyID(Long index_SupplierCompanyID) {
        Index_SupplierCompanyID = index_SupplierCompanyID;
    }

    public Long getIndex_CustomerID() {
        return Index_CustomerID;
    }

    public void setIndex_CustomerID(Long index_CustomerID) {
        Index_CustomerID = index_CustomerID;
    }

    public Long getIndex_CustomerCompanyID() {
        return Index_CustomerCompanyID;
    }

    public void setIndex_CustomerCompanyID(Long index_CustomerCompanyID) {
        Index_CustomerCompanyID = index_CustomerCompanyID;
    }

    public Long getIndex_ShipMode() {
        return Index_ShipMode;
    }

    public void setIndex_ShipMode(Long index_ShipMode) {
        Index_ShipMode = index_ShipMode;
    }

    public Long getIndex_Pick() {
        return Index_Pick;
    }

    public void setIndex_Pick(Long index_Pick) {
        Index_Pick = index_Pick;
    }

    public Long getIndex_Delivery() {
        return Index_Delivery;
    }

    public void setIndex_Delivery(Long index_Delivery) {
        Index_Delivery = index_Delivery;
    }

    public Long getIndex_Creator() {
        return Index_Creator;
    }

    public void setIndex_Creator(Long index_Creator) {
        Index_Creator = index_Creator;
    }

    public Long getIndex_CreatorCompanyID() {
        return Index_CreatorCompanyID;
    }

    public void setIndex_CreatorCompanyID(Long index_CreatorCompanyID) {
        Index_CreatorCompanyID = index_CreatorCompanyID;
    }

    public Date getIndex_CreateTime() {
        return Index_CreateTime;
    }

    public void setIndex_CreateTime(Date index_CreateTime) {
        Index_CreateTime = index_CreateTime;
    }

    public Long getIndex_Confirmer() {
        return Index_Confirmer;
    }

    public void setIndex_Confirmer(Long index_Confirmer) {
        Index_Confirmer = index_Confirmer;
    }

    public Date getIndex_ConfirmTime() {
        return Index_ConfirmTime;
    }

    public void setIndex_ConfirmTime(Date index_ConfirmTime) {
        Index_ConfirmTime = index_ConfirmTime;
    }

    public Long getIndex_Singer() {
        return Index_Singer;
    }

    public void setIndex_Singer(Long index_Singer) {
        Index_Singer = index_Singer;
    }

    public Date getIndex_SignTime() {
        return Index_SignTime;
    }

    public void setIndex_SignTime(Date index_SignTime) {
        Index_SignTime = index_SignTime;
    }

    public String getIndex_ReceiptDoc() {
        return Index_ReceiptDoc;
    }

    public void setIndex_ReceiptDoc(String index_ReceiptDoc) {
        Index_ReceiptDoc = index_ReceiptDoc;
    }

    public String getIndex_Exception() {
        return Index_Exception;
    }

    public void setIndex_Exception(String index_Exception) {
        Index_Exception = index_Exception;
    }

    public int getIndex_Invalid() {
        return Index_Invalid;
    }

    public void setIndex_Invalid(int index_Invalid) {
        Index_Invalid = index_Invalid;
    }

    public String getIndex_Comments() {
        return Index_Comments;
    }

    public void setIndex_Comments(String index_Comments) {
        Index_Comments = index_Comments;
    }

    public Long getIndex_OnLoad() {
        return Index_OnLoad;
    }

    public void setIndex_OnLoad(Long index_OnLoad) {
        Index_OnLoad = index_OnLoad;
    }

    public Long getIndex_OffLoad() {
        return Index_OffLoad;
    }

    public void setIndex_OffLoad(Long index_OffLoad) {
        Index_OffLoad = index_OffLoad;
    }

    public Long getIndex_Insurance() {
        return Index_Insurance;
    }

    public void setIndex_Insurance(Long index_Insurance) {
        Index_Insurance = index_Insurance;
    }

    public BigDecimal getIndex_VolumeAddition() {
        return Index_VolumeAddition;
    }

    public void setIndex_VolumeAddition(BigDecimal index_VolumeAddition) {
        Index_VolumeAddition = index_VolumeAddition;
    }

    public BigDecimal getIndex_WeightAddition() {
        return Index_WeightAddition;
    }

    public void setIndex_WeightAddition(BigDecimal index_WeightAddition) {
        Index_WeightAddition = index_WeightAddition;
    }

    public String getIndex_Description() {
        return Index_Description;
    }

    public void setIndex_Description(String index_Description) {
        Index_Description = index_Description;
    }

    public BigDecimal getIndex_CarVolume() {
        return Index_CarVolume;
    }

    public void setIndex_CarVolume(BigDecimal index_CarVolume) {
        Index_CarVolume = index_CarVolume;
    }

    public BigDecimal getIndex_CarWeight() {
        return Index_CarWeight;
    }

    public void setIndex_CarWeight(BigDecimal index_CarWeight) {
        Index_CarWeight = index_CarWeight;
    }

    public Long getIndex_Combined() {
        return Index_Combined;
    }

    public void setIndex_Combined(Long index_Combined) {
        Index_Combined = index_Combined;
    }

    public Long getIndex_CustomerSymbolID() {
        return Index_CustomerSymbolID;
    }

    public void setIndex_CustomerSymbolID(Long index_CustomerSymbolID) {
        Index_CustomerSymbolID = index_CustomerSymbolID;
    }

    public Long getIndex_SupplierSymbolID() {
        return Index_SupplierSymbolID;
    }

    public void setIndex_SupplierSymbolID(Long index_SupplierSymbolID) {
        Index_SupplierSymbolID = index_SupplierSymbolID;
    }

    public String getIndex_ReceiptDoc1() {
        return Index_ReceiptDoc1;
    }

    public void setIndex_ReceiptDoc1(String index_ReceiptDoc1) {
        Index_ReceiptDoc1 = index_ReceiptDoc1;
    }

    public String getIndex_ReceiptDoc2() {
        return Index_ReceiptDoc2;
    }

    public void setIndex_ReceiptDoc2(String index_ReceiptDoc2) {
        Index_ReceiptDoc2 = index_ReceiptDoc2;
    }

    public String getIndex_ReceiptDoc3() {
        return Index_ReceiptDoc3;
    }

    public void setIndex_ReceiptDoc3(String index_ReceiptDoc3) {
        Index_ReceiptDoc3 = index_ReceiptDoc3;
    }

    public String getIndex_FromContact() {
        return Index_FromContact;
    }

    public void setIndex_FromContact(String index_FromContact) {
        Index_FromContact = index_FromContact;
    }

    public String getIndex_ToContact() {
        return Index_ToContact;
    }

    public void setIndex_ToContact(String index_ToContact) {
        Index_ToContact = index_ToContact;
    }

    public BigDecimal getIndex_GoodsValue() {
        return Index_GoodsValue;
    }

    public void setIndex_GoodsValue(BigDecimal index_GoodsValue) {
        Index_GoodsValue = index_GoodsValue;
    }

    public Long getIndex_PrevOrderID() {
        return Index_PrevOrderID;
    }

    public void setIndex_PrevOrderID(Long index_PrevOrderID) {
        Index_PrevOrderID = index_PrevOrderID;
    }

    public String getIndex_DeviceCode() {
        return Index_DeviceCode;
    }

    public void setIndex_DeviceCode(String index_DeviceCode) {
        Index_DeviceCode = index_DeviceCode;
    }

    public BigDecimal getIndex_Payment() {
        return Index_Payment;
    }

    public void setIndex_Payment(BigDecimal index_Payment) {
        Index_Payment = index_Payment;
    }

    public BigDecimal getIndex_Payable() {
        return Index_Payable;
    }

    public void setIndex_Payable(BigDecimal index_Payable) {
        Index_Payable = index_Payable;
    }

    public String getIndex_GoodsLst() {
        return Index_GoodsLst;
    }

    public void setIndex_GoodsLst(String index_GoodsLst) {
        Index_GoodsLst = index_GoodsLst;
    }

    public Date getIndex_RealFromTime() {
        return Index_RealFromTime;
    }

    public void setIndex_RealFromTime(Date index_RealFromTime) {
        Index_RealFromTime = index_RealFromTime;
    }

    public Date getIndex_RealToTime() {
        return Index_RealToTime;
    }

    public void setIndex_RealToTime(Date index_RealToTime) {
        Index_RealToTime = index_RealToTime;
    }

    public String getIndex_ReceiptDoc4() {
        return Index_ReceiptDoc4;
    }

    public void setIndex_ReceiptDoc4(String index_ReceiptDoc4) {
        Index_ReceiptDoc4 = index_ReceiptDoc4;
    }

    public String getIndex_ReceiptDoc5() {
        return Index_ReceiptDoc5;
    }

    public void setIndex_ReceiptDoc5(String index_ReceiptDoc5) {
        Index_ReceiptDoc5 = index_ReceiptDoc5;
    }

    public String getIndex_ReceiptDoc6() {
        return Index_ReceiptDoc6;
    }

    public void setIndex_ReceiptDoc6(String index_ReceiptDoc6) {
        Index_ReceiptDoc6 = index_ReceiptDoc6;
    }

    public String getIndex_ReceiptDoc7() {
        return Index_ReceiptDoc7;
    }

    public void setIndex_ReceiptDoc7(String index_ReceiptDoc7) {
        Index_ReceiptDoc7 = index_ReceiptDoc7;
    }

    public int getIndex_RollBack() {
        return Index_RollBack;
    }

    public void setIndex_RollBack(int index_RollBack) {
        Index_RollBack = index_RollBack;
    }

    public Date getIndex_ReceipTime() {
        return Index_ReceipTime;
    }

    public void setIndex_ReceipTime(Date index_ReceipTime) {
        Index_ReceipTime = index_ReceipTime;
    }

    public String getIndex_FromOperator() {
        return Index_FromOperator;
    }

    public void setIndex_FromOperator(String index_FromOperator) {
        Index_FromOperator = index_FromOperator;
    }

    public Long getIndex_TerminalOrderID() {
        return Index_TerminalOrderID;
    }

    public void setIndex_TerminalOrderID(Long index_TerminalOrderID) {
        Index_TerminalOrderID = index_TerminalOrderID;
    }

    public String getIndex_TerminalOrderCode() {
        return Index_TerminalOrderCode;
    }

    public void setIndex_TerminalOrderCode(String index_TerminalOrderCode) {
        Index_TerminalOrderCode = index_TerminalOrderCode;
    }

    public String getIndex_CustomerName() {
        return Index_CustomerName;
    }

    public void setIndex_CustomerName(String index_CustomerName) {
        Index_CustomerName = index_CustomerName;
    }

    public String getIndex_SupplierName() {
        return Index_SupplierName;
    }

    public void setIndex_SupplierName(String index_SupplierName) {
        Index_SupplierName = index_SupplierName;
    }

    public String getIndex_CreatorCompanyName() {
        return Index_CreatorCompanyName;
    }

    public void setIndex_CreatorCompanyName(String index_CreatorCompanyName) {
        Index_CreatorCompanyName = index_CreatorCompanyName;
    }

    public String getIndex_CustomerSymbolName() {
        return Index_CustomerSymbolName;
    }

    public void setIndex_CustomerSymbolName(String index_CustomerSymbolName) {
        Index_CustomerSymbolName = index_CustomerSymbolName;
    }

    public String getIndex_SupplierSymbolName() {
        return Index_SupplierSymbolName;
    }

    public void setIndex_SupplierSymbolName(String index_SupplierSymbolName) {
        Index_SupplierSymbolName = index_SupplierSymbolName;
    }

    public Long getIndex_TotalCost() {
        return Index_TotalCost;
    }

    public void setIndex_TotalCost(Long index_TotalCost) {
        Index_TotalCost = index_TotalCost;
    }

    public Long getIndex_TotalAmount() {
        return Index_TotalAmount;
    }

    public void setIndex_TotalAmount(Long index_TotalAmount) {
        Index_TotalAmount = index_TotalAmount;
    }

    public Long getIndex_TotalWeight() {
        return Index_TotalWeight;
    }

    public void setIndex_TotalWeight(Long index_TotalWeight) {
        Index_TotalWeight = index_TotalWeight;
    }

    public Long getIndex_TotalVolume() {
        return Index_TotalVolume;
    }

    public void setIndex_TotalVolume(Long index_TotalVolume) {
        Index_TotalVolume = index_TotalVolume;
    }

    public Long getIndex_CombinedAmount() {
        return Index_CombinedAmount;
    }

    public void setIndex_CombinedAmount(Long index_CombinedAmount) {
        Index_CombinedAmount = index_CombinedAmount;
    }

    public Long getIndex_CombinedCost() {
        return Index_CombinedCost;
    }

    public void setIndex_CombinedCost(Long index_CombinedCost) {
        Index_CombinedCost = index_CombinedCost;
    }

    public Long getIndex_CombinedWeight() {
        return Index_CombinedWeight;
    }

    public void setIndex_CombinedWeight(Long index_CombinedWeight) {
        Index_CombinedWeight = index_CombinedWeight;
    }

    public Long getIndex_CombinedVolume() {
        return Index_CombinedVolume;
    }

    public void setIndex_CombinedVolume(Long index_CombinedVolume) {
        Index_CombinedVolume = index_CombinedVolume;
    }

    public Long getIndex_CombinedOrderAmount() {
        return Index_CombinedOrderAmount;
    }

    public void setIndex_CombinedOrderAmount(Long index_CombinedOrderAmount) {
        Index_CombinedOrderAmount = index_CombinedOrderAmount;
    }

    public String getIndex_CombinedFrom() {
        return Index_CombinedFrom;
    }

    public void setIndex_CombinedFrom(String index_CombinedFrom) {
        Index_CombinedFrom = index_CombinedFrom;
    }

    public String getIndex_CombinedTo() {
        return Index_CombinedTo;
    }

    public void setIndex_CombinedTo(String index_CombinedTo) {
        Index_CombinedTo = index_CombinedTo;
    }

    public int getIndex_TCacheReady() {
        return Index_TCacheReady;
    }

    public void setIndex_TCacheReady(int index_TCacheReady) {
        Index_TCacheReady = index_TCacheReady;
    }

    public Date getIndex_StartMsgTime() {
        return Index_StartMsgTime;
    }

    public void setIndex_StartMsgTime(Date index_StartMsgTime) {
        Index_StartMsgTime = index_StartMsgTime;
    }

    public Date getIndex_ArriveMsgTime() {
        return Index_ArriveMsgTime;
    }

    public void setIndex_ArriveMsgTime(Date index_ArriveMsgTime) {
        Index_ArriveMsgTime = index_ArriveMsgTime;
    }

    public String getIndex_VerifyCode() {
        return Index_VerifyCode;
    }

    public void setIndex_VerifyCode(String index_VerifyCode) {
        Index_VerifyCode = index_VerifyCode;
    }

    public Date getIndex_DeviceCreatTime() {
        return Index_DeviceCreatTime;
    }

    public void setIndex_DeviceCreatTime(Date index_DeviceCreatTime) {
        Index_DeviceCreatTime = index_DeviceCreatTime;
    }

    public Date getIndex_GPSStartTime() {
        return Index_GPSStartTime;
    }

    public void setIndex_GPSStartTime(Date index_GPSStartTime) {
        Index_GPSStartTime = index_GPSStartTime;
    }

    public Date getIndex_DeviceBindingTime() {
        return Index_DeviceBindingTime;
    }

    public void setIndex_DeviceBindingTime(Date index_DeviceBindingTime) {
        Index_DeviceBindingTime = index_DeviceBindingTime;
    }

    public String getIndex_FromLocation() {
        return Index_FromLocation;
    }

    public void setIndex_FromLocation(String index_FromLocation) {
        Index_FromLocation = index_FromLocation;
    }

    public String getIndex_ToLocation() {
        return Index_ToLocation;
    }

    public void setIndex_ToLocation(String index_ToLocation) {
        Index_ToLocation = index_ToLocation;
    }

    public Date getIndex_ContainsBillDay() {
        return Index_ContainsBillDay;
    }

    public void setIndex_ContainsBillDay(Date index_ContainsBillDay) {
        Index_ContainsBillDay = index_ContainsBillDay;
    }

    public int getIndex_TrackType() {
        return Index_TrackType;
    }

    public void setIndex_TrackType(int index_TrackType) {
        Index_TrackType = index_TrackType;
    }

    public int getIndex_SupplierType() {
        return Index_SupplierType;
    }

    public void setIndex_SupplierType(int index_SupplierType) {
        Index_SupplierType = index_SupplierType;
    }

    public int getIndex_CusRollBack() {
        return Index_CusRollBack;
    }

    public void setIndex_CusRollBack(int index_CusRollBack) {
        Index_CusRollBack = index_CusRollBack;
    }

    public int getIndex_Fromtype() {
        return Index_Fromtype;
    }

    public void setIndex_Fromtype(int index_Fromtype) {
        Index_Fromtype = index_Fromtype;
    }

    public int getIndex_Totype() {
        return Index_Totype;
    }

    public void setIndex_Totype(int index_Totype) {
        Index_Totype = index_Totype;
    }

    public Long getIndex_RollBackOrderID() {
        return Index_RollBackOrderID;
    }

    public void setIndex_RollBackOrderID(Long index_RollBackOrderID) {
        Index_RollBackOrderID = index_RollBackOrderID;
    }

    public String getIndex_tpPrint() {
        return Index_tpPrint;
    }

    public void setIndex_tpPrint(String index_tpPrint) {
        Index_tpPrint = index_tpPrint;
    }

    public int getIndex_ExceptionType() {
        return Index_ExceptionType;
    }

    public void setIndex_ExceptionType(int index_ExceptionType) {
        Index_ExceptionType = index_ExceptionType;
    }

    public int getIndex_AssessLevel() {
        return Index_AssessLevel;
    }

    public void setIndex_AssessLevel(int index_AssessLevel) {
        Index_AssessLevel = index_AssessLevel;
    }

    public String getIndex_AssessMent() {
        return Index_AssessMent;
    }

    public void setIndex_AssessMent(String index_AssessMent) {
        Index_AssessMent = index_AssessMent;
    }

    public int getIndex_CloseMark() {
        return Index_CloseMark;
    }

    public void setIndex_CloseMark(int index_CloseMark) {
        Index_CloseMark = index_CloseMark;
    }

    public String getIndex_CloseMent() {
        return Index_CloseMent;
    }

    public void setIndex_CloseMent(String index_CloseMent) {
        Index_CloseMent = index_CloseMent;
    }

    public int getIndex_Addorder() {
        return Index_Addorder;
    }

    public void setIndex_Addorder(int index_Addorder) {
        Index_Addorder = index_Addorder;
    }

    public String getIndex_AddPacth() {
        return Index_AddPacth;
    }

    public void setIndex_AddPacth(String index_AddPacth) {
        Index_AddPacth = index_AddPacth;
    }

    public int getIndex_CarCount() {
        return Index_CarCount;
    }

    public void setIndex_CarCount(int index_CarCount) {
        Index_CarCount = index_CarCount;
    }

    public int getIndex_AutoSchedule() {
        return Index_AutoSchedule;
    }

    public void setIndex_AutoSchedule(int index_AutoSchedule) {
        Index_AutoSchedule = index_AutoSchedule;
    }

    public int getIndex_CombinePrice() {
        return Index_CombinePrice;
    }

    public void setIndex_CombinePrice(int index_CombinePrice) {
        Index_CombinePrice = index_CombinePrice;
    }

    public int getIndex_OrderType() {
        return Index_OrderType;
    }

    public void setIndex_OrderType(int index_OrderType) {
        Index_OrderType = index_OrderType;
    }

    public BigDecimal getIndex_ExceptionCost() {
        return Index_ExceptionCost;
    }

    public void setIndex_ExceptionCost(BigDecimal index_ExceptionCost) {
        Index_ExceptionCost = index_ExceptionCost;
    }

    public int getIndex_BeSplit() {
        return Index_BeSplit;
    }

    public void setIndex_BeSplit(int index_BeSplit) {
        Index_BeSplit = index_BeSplit;
    }

    public int getIndex_SplitType() {
        return Index_SplitType;
    }

    public void setIndex_SplitType(int index_SplitType) {
        Index_SplitType = index_SplitType;
    }

    public int getIndex_signinfotype() {
        return Index_signinfotype;
    }

    public void setIndex_signinfotype(int index_signinfotype) {
        Index_signinfotype = index_signinfotype;
    }

    public String getIndex_signinfo() {
        return Index_signinfo;
    }

    public void setIndex_signinfo(String index_signinfo) {
        Index_signinfo = index_signinfo;
    }

    @Override
    public String toString() {
        return "OrderIndex{" +
                "Index_ID=" + Index_ID +
                ", Index_GUID='" + Index_GUID + '\'' +
                ", opt_status=" + opt_status +
                ", Index_Code='" + Index_Code + '\'' +
                ", Index_PactCode='" + Index_PactCode + '\'' +
                ", Index_EndUserID=" + Index_EndUserID +
                ", Index_EndUserName='" + Index_EndUserName + '\'' +
                ", Index_FromManID=" + Index_FromManID +
                ", Index_FromMan='" + Index_FromMan + '\'' +
                ", Index_From='" + Index_From + '\'' +
                ", Index_FromProvince=" + Index_FromProvince +
                ", Index_FromCity=" + Index_FromCity +
                ", Index_FromDistrict=" + Index_FromDistrict +
                ", Index_FromTime=" + Index_FromTime +
                ", Index_ToManID=" + Index_ToManID +
                ", Index_ToMan='" + Index_ToMan + '\'' +
                ", Index_To='" + Index_To + '\'' +
                ", Index_ToProvince=" + Index_ToProvince +
                ", Index_ToCity=" + Index_ToCity +
                ", Index_ToDistrict=" + Index_ToDistrict +
                ", Index_ToTime='" + Index_ToTime + '\'' +
                ", Index_TransportMode=" + Index_TransportMode +
                ", Index_GoodsCategory=" + Index_GoodsCategory +
                ", Index_PackageMode=" + Index_PackageMode +
                ", Index_ChargeMode=" + Index_ChargeMode +
                ", Index_PriceUnit=" + Index_PriceUnit +
                ", Index_Status=" + Index_Status +
                ", Index_StatusTime=" + Index_StatusTime +
                ", Index_SrcOrderID=" + Index_SrcOrderID +
                ", Index_RootOrderID=" + Index_RootOrderID +
                ", Index_SrcClass=" + Index_SrcClass +
                ", Index_Kms=" + Index_Kms +
                ", Index_CarType=" + Index_CarType +
                ", Index_CarLength=" + Index_CarLength +
                ", Index_DriverID=" + Index_DriverID +
                ", Index_CarID=" + Index_CarID +
                ", Index_SupplierID=" + Index_SupplierID +
                ", Index_SupplierCompanyID=" + Index_SupplierCompanyID +
                ", Index_CustomerID=" + Index_CustomerID +
                ", Index_CustomerCompanyID=" + Index_CustomerCompanyID +
                ", Index_ShipMode=" + Index_ShipMode +
                ", Index_Pick=" + Index_Pick +
                ", Index_Delivery=" + Index_Delivery +
                ", Index_Creator=" + Index_Creator +
                ", Index_CreatorCompanyID=" + Index_CreatorCompanyID +
                ", Index_CreateTime=" + Index_CreateTime +
                ", Index_Confirmer=" + Index_Confirmer +
                ", Index_ConfirmTime=" + Index_ConfirmTime +
                ", Index_Singer=" + Index_Singer +
                ", Index_SignTime=" + Index_SignTime +
                ", Index_ReceiptDoc='" + Index_ReceiptDoc + '\'' +
                ", Index_Exception='" + Index_Exception + '\'' +
                ", Index_Invalid=" + Index_Invalid +
                ", Index_Comments='" + Index_Comments + '\'' +
                ", Index_OnLoad=" + Index_OnLoad +
                ", Index_OffLoad=" + Index_OffLoad +
                ", Index_Insurance=" + Index_Insurance +
                ", Index_VolumeAddition=" + Index_VolumeAddition +
                ", Index_WeightAddition=" + Index_WeightAddition +
                ", Index_Description='" + Index_Description + '\'' +
                ", Index_CarVolume=" + Index_CarVolume +
                ", Index_CarWeight=" + Index_CarWeight +
                ", Index_Combined=" + Index_Combined +
                ", Index_CustomerSymbolID=" + Index_CustomerSymbolID +
                ", Index_SupplierSymbolID=" + Index_SupplierSymbolID +
                ", Index_ReceiptDoc1='" + Index_ReceiptDoc1 + '\'' +
                ", Index_ReceiptDoc2='" + Index_ReceiptDoc2 + '\'' +
                ", Index_ReceiptDoc3='" + Index_ReceiptDoc3 + '\'' +
                ", Index_FromContact='" + Index_FromContact + '\'' +
                ", Index_ToContact='" + Index_ToContact + '\'' +
                ", Index_GoodsValue=" + Index_GoodsValue +
                ", Index_PrevOrderID=" + Index_PrevOrderID +
                ", Index_DeviceCode='" + Index_DeviceCode + '\'' +
                ", Index_Payment=" + Index_Payment +
                ", Index_Payable=" + Index_Payable +
                ", Index_GoodsLst='" + Index_GoodsLst + '\'' +
                ", Index_RealFromTime=" + Index_RealFromTime +
                ", Index_RealToTime=" + Index_RealToTime +
                ", Index_ReceiptDoc4='" + Index_ReceiptDoc4 + '\'' +
                ", Index_ReceiptDoc5='" + Index_ReceiptDoc5 + '\'' +
                ", Index_ReceiptDoc6='" + Index_ReceiptDoc6 + '\'' +
                ", Index_ReceiptDoc7='" + Index_ReceiptDoc7 + '\'' +
                ", Index_RollBack=" + Index_RollBack +
                ", Index_ReceipTime=" + Index_ReceipTime +
                ", Index_FromOperator='" + Index_FromOperator + '\'' +
                ", Index_TerminalOrderID=" + Index_TerminalOrderID +
                ", Index_TerminalOrderCode='" + Index_TerminalOrderCode + '\'' +
                ", Index_CustomerName='" + Index_CustomerName + '\'' +
                ", Index_SupplierName='" + Index_SupplierName + '\'' +
                ", Index_CreatorCompanyName='" + Index_CreatorCompanyName + '\'' +
                ", Index_CustomerSymbolName='" + Index_CustomerSymbolName + '\'' +
                ", Index_SupplierSymbolName='" + Index_SupplierSymbolName + '\'' +
                ", Index_TotalCost=" + Index_TotalCost +
                ", Index_TotalAmount=" + Index_TotalAmount +
                ", Index_TotalWeight=" + Index_TotalWeight +
                ", Index_TotalVolume=" + Index_TotalVolume +
                ", Index_CombinedAmount=" + Index_CombinedAmount +
                ", Index_CombinedCost=" + Index_CombinedCost +
                ", Index_CombinedWeight=" + Index_CombinedWeight +
                ", Index_CombinedVolume=" + Index_CombinedVolume +
                ", Index_CombinedOrderAmount=" + Index_CombinedOrderAmount +
                ", Index_CombinedFrom='" + Index_CombinedFrom + '\'' +
                ", Index_CombinedTo='" + Index_CombinedTo + '\'' +
                ", Index_TCacheReady=" + Index_TCacheReady +
                ", Index_StartMsgTime=" + Index_StartMsgTime +
                ", Index_ArriveMsgTime=" + Index_ArriveMsgTime +
                ", Index_VerifyCode='" + Index_VerifyCode + '\'' +
                ", Index_DeviceCreatTime=" + Index_DeviceCreatTime +
                ", Index_GPSStartTime=" + Index_GPSStartTime +
                ", Index_DeviceBindingTime=" + Index_DeviceBindingTime +
                ", Index_FromLocation='" + Index_FromLocation + '\'' +
                ", Index_ToLocation='" + Index_ToLocation + '\'' +
                ", Index_ContainsBillDay=" + Index_ContainsBillDay +
                ", Index_TrackType=" + Index_TrackType +
                ", Index_SupplierType=" + Index_SupplierType +
                ", Index_CusRollBack=" + Index_CusRollBack +
                ", Index_Fromtype=" + Index_Fromtype +
                ", Index_Totype=" + Index_Totype +
                ", Index_RollBackOrderID=" + Index_RollBackOrderID +
                ", Index_tpPrint='" + Index_tpPrint + '\'' +
                ", Index_ExceptionType=" + Index_ExceptionType +
                ", Index_AssessLevel=" + Index_AssessLevel +
                ", Index_AssessMent='" + Index_AssessMent + '\'' +
                ", Index_CloseMark=" + Index_CloseMark +
                ", Index_CloseMent='" + Index_CloseMent + '\'' +
                ", Index_Addorder=" + Index_Addorder +
                ", Index_AddPacth='" + Index_AddPacth + '\'' +
                ", Index_CarCount=" + Index_CarCount +
                ", Index_AutoSchedule=" + Index_AutoSchedule +
                ", Index_CombinePrice=" + Index_CombinePrice +
                ", Index_OrderType=" + Index_OrderType +
                ", Index_ExceptionCost=" + Index_ExceptionCost +
                ", Index_BeSplit=" + Index_BeSplit +
                ", Index_SplitType=" + Index_SplitType +
                ", Index_signinfotype=" + Index_signinfotype +
                ", Index_signinfo='" + Index_signinfo + '\'' +
                '}';
    }
}
