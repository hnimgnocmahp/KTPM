package DAO;

import DTO.Building;
import config.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BuildingDAO implements DAOInterface<Building> {

    @Override
    public int insert(Building building) {
        int ketQua = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Building (buildingId, nameBuilding, city_Building, district_Building, address_Building, numberOfApartment_Building) VALUES (?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, building.getBuildingId());
            preparedStatement.setString(2, building.getNameBuilding());
            preparedStatement.setString(3, building.getCity_Building());
            preparedStatement.setString(4, building.getDistrict_Building());
            preparedStatement.setString(5, building.getAddress_Building());
            preparedStatement.setInt(6, building.getNumberOfApartment_Building());

            ketQua = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int update(Building building) {
        int ketQua = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Building SET nameBuilding = ?, city_Building = ?, district_Building = ?, address_Building = ?, numberOfApartment_Building = ? WHERE buildingId = ?");

            preparedStatement.setString(1, building.getNameBuilding());
            preparedStatement.setString(2, building.getCity_Building());
            preparedStatement.setString(3, building.getDistrict_Building());
            preparedStatement.setString(4, building.getAddress_Building());
            preparedStatement.setInt(5, building.getNumberOfApartment_Building());
            preparedStatement.setString(6, building.getBuildingId());

            ketQua = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    @Override
    public int delete(String ID) {
        int ketQua = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Building WHERE buildingId = ?");
            preparedStatement.setString(1, ID);

            ketQua = preparedStatement.executeUpdate();

            preparedStatement.close();
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ
        }
        return ketQua;
    }

    @Override
    public ArrayList<Building> selectAll() {
        ArrayList<Building> buildings = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Building";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String buildingId = resultSet.getString("buildingID");
                String nameBuilding = resultSet.getString("nameBuilding");
                String city_Building = resultSet.getString("cityBuilding");
                String district_Building = resultSet.getString("district_Building");
                String address_Building = resultSet.getString("address_Building");
                int numberOfApartment_Building = resultSet.getInt("numberOfApartment_Building");

                Building building = new Building(buildingId, nameBuilding, city_Building, district_Building, address_Building, numberOfApartment_Building);
                buildings.add(building);
            }

            resultSet.close();
            statement.close();
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buildings;
    }

    @Override
    public Building selectById(String ID) {
        Building building = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM Building WHERE buildingId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String buildingId = resultSet.getString("buildingID");
                String nameBuilding = resultSet.getString("nameBuilding");
                String city_Building = resultSet.getString("cityBuilding");
                String district_Building = resultSet.getString("district_Building");
                String address_Building = resultSet.getString("address_Building");
                int numberOfApartment_Building = resultSet.getInt("numberOfApartment_Building");

                building = new Building(buildingId, nameBuilding, city_Building, district_Building, address_Building, numberOfApartment_Building);
            }

            resultSet.close();
            preparedStatement.close();
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return building;
    }
}
