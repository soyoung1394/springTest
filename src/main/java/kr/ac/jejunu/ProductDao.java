package kr.ac.jejunu;

import java.sql.*;

public abstract class ProductDao {
    public Product get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setTitle(resultSet.getString("title"));
        product.setPrice(resultSet.getInt("price"));

        resultSet.close();

        preparedStatement.close();
        connection.close();

        return product;
    }
    public Long insert(Product product) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into product(title, price) values(?, ?)");
        preparedStatement.setString(1, product.getTitle());
        preparedStatement.setInt(2, product.getPrice());

        preparedStatement.executeUpdate();

        preparedStatement=connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        Long id=resultSet.getLong(1);
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return id;
    }
    abstract public Connection getConnection() throws ClassNotFoundException, SQLException ;
}
