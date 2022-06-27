package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interaction;

public class GenesDao {

	public List<Genes> getAllGenes(Map<String, Genes> idMap) {
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome "
				+ "from genes "
				+ "where essential='essential' ";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), res.getString("Essential"), res.getInt("Chromosome"));
				idMap.put(res.getString("GeneID"), genes);
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Interaction> getInteractions(Map<String, Genes> idMap) {

		List<Interaction> result = new ArrayList<>();
		String sql = "select * "+
		"from interactions "
		+"where geneID1<>geneID2 ";

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				if (idMap.containsKey(res.getString("geneID1")) && idMap.containsKey(res.getString("geneID2"))) {

					Interaction i = new Interaction(idMap.get(res.getString("geneID1")),
							idMap.get(res.getString("geneID2")), res.getString("type"),
							res.getDouble("Expression_Corr"));
					result.add(i);
				}
			}

			res.close();
			st.close();
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
