package com.sunshine.fusys.enums;


import net.sf.json.JSONObject;

/**
 * 
 * 创建人：fantasy<br>
 * 创建时间：2013-3-25 <br>
 * 功能描述： 公共状态<br>
 */
public enum CommonStateEnum {

	VERSION_ERROR(101, 1, "版本错误"),

	OK(200, 1, "成功"),

	AUTH_SIGN(401, 1, "签名错误"),

	AUTH_IP(401, 2, "IP受限"),

	AUTH_INVALID(401, 3, "合作商无效"),

	AUTH_TIME_STAMP(401, 4, "请求过期"),

	NOT_FOUND(404, 1, "资源不存在"),

	SERVER_ERROR(500, 1, "服务器异常,请稍后再试"),

	NO_THREAD_ERROR(500, 2, "没有足够的线程"),

	BAD_GATEWAY(502, 1, "第三方服务器异常"),

	TIME_OUT(504, 1, "连接超时"),

	BADREQ_PARA(403, 1, "参数错误"),

	BADREQ_PARA_NULL(403, 2, "参数不能为空"),

	CARDNO_PASS_ERROR(403, 101, "卡号或密码错误"),

	CARDNO_ERROR(403, 102, "此卡号不存在"),

	CARDNO_IS_LOCK(403, 103, "此卡已充值或者作废"),

	CARDNO_IS_NOT_ENOUGH(403, 104, "卡库存不足"),

	NO_THIS_PASSNAME(403, 105, "无此通行证"),

	PASSWORD_ERROR(403, 106, "密码错误"), // 新增

	PASSWORD_FORMAT_ERROR(403, 107, "密码格式错误"), // 新增

	NO_GAME(403, 108, "无此游戏或已经关闭充值"),

	NO_GAME_AREA(403, 109, "无此游戏大区或已经关闭充值"),

	NO_GAME_PAYWAY(403, 110, "无此游戏的支付方式"),

	NO_GAME_PASSTYPE(403, 111, "无此游戏的对应的通行证类别"),

	NO_GAME_CARDTYPE(403, 112, "无此游戏的对应的卡类别"),

	CARDTYPE_ERROR(403, 113, "无此类卡类型"),

	ORDERPLAY_ERROR(403, 114, "订单不存在或已失效"),

	ORDERPLAY_SUCED_ERROR(403, 115, "订单已经完成充值"),
	// 锁定 2 强制锁定 3 申请交易锁定 4 身份证重制锁定
	PASSPORT_LOCK(403, 116, "通行证锁定"),

	PASSPORT_FORCE_LOCK(403, 117, "通行证锁定"),

	PASSPORT_TRANC_LOCK(403, 118, "通行证交易锁定"),

	PASSPORT_PASSCARD_LOCK(403, 119, "通行证身份证充值锁定"),

	DATA_ERROR(403, 120, "数据异常"),

	LOGIN_ERROR(403, 121, "未登陆"),

	BAD_VALI_CODE(403, 122, "验证码错误"),

	NO_PAY_WAY(403, 123, "无此对应的支付方式"),

	CALLBACK_ERROR(403, 124, "回调错误"),

	NO_NEED_CHECK_ROLE(403, 125, "此游戏不需要验证角色"),

	NOTICE_URL_CLOSED(403, 126, "通知URL已关闭"),

	NO_ROLE_INGAME(403, 127, "游戏大区未创建角色"),

	ZFB_MOBILE_PAY_DIRECT_FAIL(403, 128, "手机网页即时到账授权失败！"),

	JX_MOBILE_PAY_DIRECT_FAIL(403, 130, "捷讯手机卡支付签名验证失败！"),

	GET_ASEKEY_FAIL(505,1, "获取密匙失败！"),

	ERROR_ORDER_MONEY(403, 129, "订单金额不符！");

	private int state;

	private int stateCode;

	private String message;

	private CommonStateEnum(int state, int stateCode, String message) {
		this.state = state;
		this.stateCode = stateCode;
		this.message = message;
	}

	public int getState() {
		return state;
	}

	public int getStateCode() {
		return stateCode;
	}

	public final String getMessage() {
		return message;
	}

	public final static String getMessage(int state, int stateCode) {
		for (int i = 0; i < CommonStateEnum.values().length; i++) {
			if (CommonStateEnum.values()[i].getState() == state
					&& CommonStateEnum.values()[i].getStateCode() == stateCode) {
				return CommonStateEnum.values()[i].getMessage();
			}
		}
		return null;
	}

	public final static CommonStateEnum getCommonStateEnum(int state, int stateCode) {
		for (int i = 0; i < CommonStateEnum.values().length; i++) {
			if (CommonStateEnum.values()[i].getState() == state
					&& CommonStateEnum.values()[i].getStateCode() == stateCode) {
				return CommonStateEnum.values()[i];
			}
		}
		return null;
	}

	public String getJSONString() {
		JSONObject obj = new JSONObject();
		obj.put(FieldEnum.CODE.getField(), this.getState());
		obj.put(FieldEnum.STATE_CODE.getField(), this.getStateCode());
		obj.put(FieldEnum.MESSAGE.getField(), this.getMessage());
		return obj.toString();
	}

	/**
	 * CommonStateEnum类的字段值
	 */
	public static enum FieldEnum {

		CODE("code"), STATE_CODE("stateCode"), MESSAGE("message");

		private String field;

		private FieldEnum(String field) {
			this.field = field;
		}

		public String getField() {
			return this.field;
		}
	}
}