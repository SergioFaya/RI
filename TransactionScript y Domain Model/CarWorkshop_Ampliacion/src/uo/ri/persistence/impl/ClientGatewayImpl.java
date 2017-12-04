package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.ClientsGateway;

public class ClientGatewayImpl implements ClientsGateway {

	private Connection con = null;
	private PreparedStatement pst = null;

	@Override
	public void setConnection(Connection con) {
		this.con = con;

	}

	@Override
	public Map<String, Object> getAllClients() throws BusinessException {
		Map<String, Object> map = new HashMap<>();
		List<Long> clients = new ArrayList<>();
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(Conf.get("SQL_SELECT_ALL_CLIENTS"));
			rs = pst.executeQuery();
			while (rs.next()) {
				clients.add(rs.getLong(1));
			}
		} catch (SQLException e) {
			throw new BusinessException("Ha ocurrido un error en la base de datos al listar clientes pongase en contacto con su proveedor");
		} finally {
			Jdbc.close(rs, pst);
		}
		map.put("clients", clients);
		return map;
	}

	@Override
	public void addClient(Map<String, Object> map) throws BusinessException {
		try {
			pst = con.prepareStatement(Conf.get("SQL_INSERT_CLIENT"));
			pst.setLong(1, (long) map.get("clientId"));
			pst.setString(2, (String) map.get("city"));
			pst.setString(3, (String) map.get("street"));
			pst.setString(4, (String) map.get("zipcode"));
			pst.setString(5, (String) map.get("apellidos"));
			pst.setString(6, (String) map.get("dni"));
			pst.setString(7, (String) map.get("nombre"));
			pst.setLong(8, (long) map.get("telefono"));
			pst.setString(9, (String) map.get("email"));
			pst.setLong(10, (long) map.get("idRecomendador"));
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new BusinessException("Ha ocurrido un error en la base de datos al añadir clientes pongase en contacto con su proveedor");
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void removeClient(Map<String, Object> map) throws BusinessException {
		try {
			pst = con.prepareStatement(Conf.get("SQL_DELETE_CLIENT"));
			pst.setLong(1, (long) map.get("id"));
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new BusinessException("Ha ocurrido un error en la base de datos al eliminar clientes pongase en contacto con su proveedor");
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void updateClient(Map<String, Object> map) throws BusinessException {
		try {
			pst = con.prepareStatement(Conf.get("SQL_UPDATE_CLIENT"));
			pst.setString(1, (String) map.get("city"));
			pst.setString(2, (String) map.get("street"));
			pst.setString(3, (String) map.get("zipcode"));
			pst.setString(4, (String) map.get("apellidos"));
			pst.setString(5, (String) map.get("dni"));
			pst.setString(6, (String) map.get("nombre"));
			pst.setLong(7, (long) map.get("telefono"));
			pst.setString(8, (String) map.get("email"));
			pst.setLong(9, (long) map.get("id"));
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new BusinessException("Ha ocurrido un error en la base de datos al actualizar clientes pongase en contacto con su proveedor");
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public Map<String,Object> exists(Map<String,Object> map) throws BusinessException{
		Map<String,Object> mapReturn = new HashMap<>();
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(Conf.get("SQL_SELECT_CLIENT"));
			pst.setLong(1, (long) map.get("id"));
			rs = pst.executeQuery();
			if(rs.next()){
				mapReturn.put("exists",true);
			}else{
				mapReturn.put("exists",false);
			}
		} catch (SQLException e) {
			throw new BusinessException("Ha ocurrido un error en la base de datos al buscar el cliente pongase en contacto con su proveedor");
		}finally {
			Jdbc.close(rs,pst);
		}
		
		return mapReturn;
	}
	
	@Override
	public Map<String, Object> getInfo(Map<String, Object> map) throws BusinessException {
		Map<String, Object> mapReturn = new HashMap<>();
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(Conf.get("SQL_SELECT_CLIENT"));
			pst.setLong(1, (long) map.get("id"));
			rs = pst.executeQuery();
			rs.next();
			mapReturn.put("id", rs.getLong(1));
			mapReturn.put("city", rs.getString(2));
			mapReturn.put("street", rs.getString(3));
			mapReturn.put("zipcode", rs.getString(4));
			mapReturn.put("apellidos", rs.getString(5));
			mapReturn.put("dni", rs.getString(6));
			mapReturn.put("nombre", rs.getString(7));
			mapReturn.put("telefono", rs.getLong(8));
			mapReturn.put("email", rs.getString(9));
			mapReturn.put("idRecomendador", rs.getString(10));
		} catch (SQLException e) {
			throw new BusinessException("Ha ocurrido un error en la base de datos al optener la informacion del cliente pongase en contacto con su proveedor");
		} finally {
			Jdbc.close(rs, pst);
		}
		if (mapReturn.isEmpty()) {
			throw new BusinessException();
		}
		return mapReturn;
	}

	@Override
	public Map<String, Object> getLatestClient() throws BusinessException {
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<>();
		try {
			pst = con.prepareStatement(Conf.get("SQL_LATEST_NUM_CLIENTES"));
			rs = pst.executeQuery();
			if (rs.next()) {
				map.put("clientId", rs.getLong(1) + 1);
			} else {
				map.put("clientId", 1L);
			}
		} catch (SQLException e) {
			throw new BusinessException("Ha ocurrido un error en la base de datos al encontrar el ultimo cliente pongase en contacto con su proveedor");
		} finally {
			Jdbc.close(rs, pst);
		}
		return map;
	}

	@Override
	public Map<String, Object> facturaPagada(Map<String, Object> map) throws BusinessException {
		ResultSet rs = null;
		Map<String, Object> mapReturn = new HashMap<>();
		try{
			pst = con.prepareStatement(Conf.get("SQL_FIND_STATUS_FACTURA_CLIENT"));
			pst.setLong(1, (long) map.get("id"));
			rs = pst.executeQuery();
			rs.next();
			mapReturn.put("status",rs.getString(1));
		} catch (SQLException e) {
			throw new BusinessException("Ha ocurrido un error en la base de datos al buscar la factura del cliente pongase en contacto con su proveedor");
		}finally {
			Jdbc.close(rs, pst);
		}
		return mapReturn;
	}

	@Override
	public List<Map<String,Object>> getClientsOfRecommender(Map<String, Object> map) throws BusinessException {
		List<Map<String,Object>> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(Conf.get("SQL_GET_RECOMMENDED_CLIENTS"));
			pst.setLong(1, (long) map.get("idRecommender"));
			rs = pst.executeQuery();
			while(rs.next()){
				Map<String,Object> mapAux = new HashMap<>();
				mapAux.put("idRecommender",rs.getLong(1));
				list.add(mapAux);
			}
		} catch (SQLException e) {
			throw new BusinessException("Ha ocurrido un error en la base de datos al buscar clientes recomendados pongase en contacto con su proveedor");
		}finally{
			Jdbc.close(rs,pst);
		}
		return list;
	}

}
