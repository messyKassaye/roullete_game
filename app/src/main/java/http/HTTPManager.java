package http;

import android.net.Uri.Builder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import projectstatic.ProjectStatic;

public class HTTPManager {
    public static String REGISTER_USERS(String user_name, String emails, String referral_code, String pass) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/create_account.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            String query = new Builder().appendQueryParameter("user_name", user_name).appendQueryParameter("email", emails).appendQueryParameter("referral_code", referral_code).appendQueryParameter("pass", pass).build().getEncodedQuery();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String LOGIN_USER(String user_name, String password) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/login.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            String query = new Builder().appendQueryParameter("user_name", user_name).appendQueryParameter("pass", password).build().getEncodedQuery();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return "no connection";
        }
    }

    public static String update_address(String email, String address) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/update_address.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            String query = new Builder().appendQueryParameter("email", email).appendQueryParameter("address", address).build().getEncodedQuery();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String update_account(String email, String account) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/update_account.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            String query = new Builder().appendQueryParameter("email", email).appendQueryParameter("amount", account).build().getEncodedQuery();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return "no connection";
        }
    }

    public static String GET_TIMER() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8000/game_timers.php").openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoInput(true);
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
            //return  response.toString();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public static String GET_GAME_VERSION() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/version.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return "no";
        }
    }

    public static String ADD_TABLE_BIT(String email, String table_no, String position) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/Add_table_no.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            String query = new Builder().appendQueryParameter("email", email).appendQueryParameter("table_no", table_no).appendQueryParameter("position", position).build().getEncodedQuery();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String REMOVE_TABLE_NO(String email) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/Remove_table_no.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            String query = new Builder().appendQueryParameter("email", email).build().getEncodedQuery();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String UPDATE_WINNERS_SEEN(String email, String id) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/update_winn_is_seen.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            String query = new Builder().appendQueryParameter("email", email).appendQueryParameter("id", id).build().getEncodedQuery();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String GET_PLAYER() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/get_player.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return "no connection";
        }
    }

    public static String ADD_WINNING_NUMBER(String number) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/add_winning_number.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            String query = new Builder().appendQueryParameter("number", number).build().getEncodedQuery();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String SHARE(String share_by, String share_to, String user_name) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/share.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            String query = new Builder().appendQueryParameter("share_by", share_by).appendQueryParameter("share_to", share_to).appendQueryParameter("user_name", user_name).build().getEncodedQuery();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String ADD_COMMISION(String referral_code, String user_name, String total_bit) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/add_commision.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            String query = new Builder().appendQueryParameter("referral_code", referral_code).appendQueryParameter("total_bit", total_bit).appendQueryParameter("user_name", user_name).build().getEncodedQuery();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String GET_CHIPS() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/get_chips.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String GET_COMMISION(String referral_code) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/get_commisions.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            String query = new Builder().appendQueryParameter("referral_code", referral_code).build().getEncodedQuery();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String GET_MY_LEVEL(String referral_code) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("http://roulette6.club/get_my_level.php").openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            String query = new Builder().appendQueryParameter("referral_code", referral_code).build().getEncodedQuery();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder response = new StringBuilder();
            while (true) {
                line = reader.readLine();
                if (line == null) {
                    return response.toString();
                }
                response.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }
}
