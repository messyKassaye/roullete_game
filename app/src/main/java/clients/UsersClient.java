package clients;

import com.example.meseret.roulette.MyLevels;
import com.squareup.okhttp.ResponseBody;

import java.util.List;

import clientsModel.AddressModel;
import clientsModel.Amounts;
import clientsModel.ChangePassword;
import clientsModel.ChipsMarket;
import clientsModel.ChipsMarketModel;
import clientsModel.Commission;
import clientsModel.CommissionModel;
import clientsModel.CompanyBitcoinAddress;
import clientsModel.LevelsModel;
import clientsModel.MaxocoinChipsValue;
import clientsModel.Player;
import clientsModel.Timer;
import clientsModel.TransactionRequest;
import clientsModel.UpdateAccount;
import clientsModel.Users;
import clientsModel.UsersData;
import clientsModel.UsersLogin;
import clientsModel.WithdrawalRequest;
import clientsModel.WithdrawalSentModel;
import model.ResponseMessage;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Meseret on 10/24/2017.
 */

public interface UsersClient {
    @POST("signup")
    Call<ResponseMessage> signup(@Body Users users);

    @POST("login")
    Call<ResponseMessage> login(@Body UsersLogin login);

    @GET("usersinfo")
    Call<UsersData> getUsersInfo(@Query("token")String token);
    @POST("update_bitcoin_address")
    Call<ResponseMessage> updateAddress(@Query("token")String token, @Body AddressModel model);

    @GET("chipsMarket")
    Call<List<ChipsMarket>> getChipsMarket(@Query("token")String token);

    @GET("getcompanyaddress")
    Call<List<CompanyBitcoinAddress>> getCompanyAddress(@Query("token")String token);

    @POST("transaction_request")
    Call<ResponseMessage> sendRequest(@Query("token")String token, @Body TransactionRequest request);

    @GET("getPlayer")
    Call<List<Player>> getPlayer(@Query("token")String token);

    @GET("update_account")
    Call<List<Amounts>> update_account(@Query("token")String token,@Query("amount")String amount);

    @POST("update_lose")
    Call<List<Amounts>> update_lose(@Query("token")String token,@Body UpdateAccount account);

    @POST("add_commission")
    Call<ResponseMessage> add_commission(@Query("token")String token, @Body Commission commission);

    @GET("game_timer")
    Call<ResponseMessage> get_timer();

    @GET("get_my_level")
    Call<List<LevelsModel>>  get_my_levels(@Query("token")String token);

    @GET("my_commission")
    Call<List<CommissionModel>> get_my_commission(@Query("token")String token);

    @POST("withdrawal")
    Call<ResponseMessage> send_request_withdrawal(@Query("token")String token,@Body WithdrawalRequest request);

    @GET("get_send_withdrawal")
    Call<List<WithdrawalSentModel>> get_withdrawal(@Query("token")String token);

    @POST("change_password")
    Call<ResponseMessage> changePassword(@Query("token")String token, @Body ChangePassword changePassword);

    @POST("change_username")
    Call<ResponseMessage> changeUsername(@Query("token")String token, @Body ChangePassword changePassword);

    @GET("get_company_address")
    Call<List<CompanyBitcoinAddress>> getccompany_address(@Query("token")String token);

    @GET("get_maxocoin_value")
    Call<List<MaxocoinChipsValue>> getMaxocoinChipsValue(@Query("token")String token);

    @POST("send_chips_request")
    Call<ResponseMessage> send_chips_request(@Query("token")String token, @Body ChipsMarketModel model);

    @GET("convert_token")
    Call<UsersData> convertToken(@Query("token")String token);
}
