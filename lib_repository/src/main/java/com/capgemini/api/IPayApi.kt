package com.capgemini.api

import com.capgemini.entity.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IPayApi {
    /**
     * 支付宝
     *
     * @return 请求结果
     */
    @FormUrlEncoded
    @POST("mockpay/1.0/redirectPayUrl")
    fun getAliPayParameters(
        @Field("token") token: String?,
        @Field("vin") vin: String?, @Field("timestamp") time: String?,
        @Field("pay_order_id") orider: String?,
        @Field("pay_gateway_product_code") code: String?
    ): Observable<BaseResponse<*>?>

    /**
     * 微信
     *
     * @return 请求结果
     */
    @FormUrlEncoded
    @POST("mockpay/1.0/redirectPayUrl")
    fun getWeChatPayParameters(
        @Field("ip") ip: String?, @Field("token") token: String?,
        @Field("vin") vin: String?, @Field("timestamp") time: String?,
        @Field("pay_order_id") order: String?,
        @Field("pay_gateway_product_code") code: String?
    ): Observable<BaseResponse<*>?>
}