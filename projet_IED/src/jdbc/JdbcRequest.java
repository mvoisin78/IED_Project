package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.Movie;

public class JdbcRequest {

	public static void getMovieInfo(Movie movie, String title) {
		try {

			String selectAddressQuery = "SELECT * FROM movie WHERE movie.Movie= ?";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(selectAddressQuery);

			preparedStatement.setString(1, title);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				movie.setTitre(title);
				movie.setDate(result.getString("ReleaseDate"));
				movie.setBudget(result.getString("ProductionBudget"));
				movie.setRevenusUS(result.getString("DomesticGross"));
				movie.setRevenusMonde(result.getString("WorldwideGross"));
				movie.setDistributeur(result.getString("Distributor"));
				movie.setGenre(result.getString("Genre"));
			}

			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	public static Movie getActorInfo(Movie movie, String title) {
		try {

			String selectAddressQuery = "SELECT ReleaseDate,Genre,Distributor FROM movie WHERE movie.Movie= ?";

			Connection dbConnection = JdbcConnection.getConnection();
			PreparedStatement preparedStatement = dbConnection.prepareStatement(selectAddressQuery);

			preparedStatement.setString(1, title);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				movie.setDate(result.getString("ReleaseDate"));
				movie.setDistributeur(result.getString("Distributor"));
				movie.setGenre(result.getString("Genre"));
			}

			preparedStatement.close();

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return movie;
	}
}
