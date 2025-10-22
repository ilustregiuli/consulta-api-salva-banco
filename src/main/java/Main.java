import com.google.gson.Gson;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {

        try(Connection conn = DB.getConnection()) {


            // criando tabela com PreparedStatement
//          String sql = "CREATE TABLE people (id SERIAL PRIMARY KEY, " +
//                "name VARCHAR(100), " +
//                "height INTEGER, " +
//                "mass INTEGER, " +
//                "hair_color VARCHAR(100), " +
//                "skin_color VARCHAR(100), " +
//                "gender VARCHAR(100) )";

//        PreparedStatement pst = conn.prepareStatement(sql)
//          pst.execute() ---> devolve um boolean dizendo se tem (true) ou não tem (false)
//          um ResultSet para retornar. Nesse caso, não tem.

            // String sql_insert = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";

            // Receber o EndPoint com caixa de diálogo:
            String url = JOptionPane.showInputDialog(null,
                    "Digite endpoint da API:",
                    "Entrada do Endpoint",
                    JOptionPane.QUESTION_MESSAGE);

            // Objeto criado para receber a resposta
            HttpResponse<String> response;

            // Cliente HTTP criado
            HttpClient client = HttpClient.newHttpClient();

            // Criando uma request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Aqui o client "send" (envia) a request criada e devolve uma reposta (response)
            response = client.send(request, HttpResponse.BodyHandlers.ofString());


            Gson gson = new Gson();
            String json = response.body();

            Personagem personagem = gson.fromJson(json, Personagem.class);

            // Vamos verificar se já existe no banco:
            String sql = "SELECT * FROM people WHERE name = ?";
            PreparedStatement pstConsulta = conn.prepareStatement(sql);
            pstConsulta.setString(1,personagem.getName());

            try(ResultSet rs = pstConsulta.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Já existe esse personagem no banco!");
                } else {
                    // Inserindo no banco:
                    sql = "INSERT INTO people (name, hair_color, skin_color, gender) VALUES (?, ?, ?, ?)";
                    PreparedStatement pst = conn.prepareStatement(sql);

                    pst.setString(1, personagem.getName());
                    pst.setString(2, personagem.getHair_color());
                    pst.setString(3, personagem.getSkin_color());
                    pst.setString(4, personagem.getGender());

                    int rowsAffected = pst.executeUpdate();
                    System.out.println("Linhas inseridas: " + rowsAffected);
                }
            }
            // ResultSet rs = st.executeQuery("select * from tb_product");

            // while (rs.next()) {
            // System.out.println(rs.getLong("Id") + ", " + rs.getString("Name"));
            // }
        }
    }
}

