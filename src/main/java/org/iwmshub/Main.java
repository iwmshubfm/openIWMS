package org.iwmshub;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.logging.Logger;

import io.agroal.api.AgroalDataSource;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class Main {
    @Inject
    Logger log;

    @Inject
    AgroalDataSource defaultDataSource;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public JsonArray hello() throws SQLException {
        log.info("Hello!");
        JsonArray jsonArray = new JsonArray();
        try {
            Connection connection = defaultDataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT admin_fields.table_name,admin_tabls.title,admin_fields.field_name,admin_fields.title,'FUCK' as a FROM admin_fields join admin_tabls on admin_fields.table_name = admin_tabls.table_name");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.put("table_name", resultSet.getString("table_name"));
                jsonObject.put("field_name", resultSet.getString("field_name"));
                jsonObject.put("title", resultSet.getString("admin_fields.title"));
                jsonObject.put("title_table", resultSet.getString("admin_tabls.title"));
                jsonObject.put("a", resultSet.getString("a"));
                jsonArray.add(jsonObject);
            }
        } catch (Exception e) {

        }
        return jsonArray;
    }
}
