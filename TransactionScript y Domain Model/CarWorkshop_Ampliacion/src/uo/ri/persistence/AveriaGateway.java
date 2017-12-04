package uo.ri.persistence;

import java.sql.Connection;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface AveriaGateway extends Gateway{
	
	@Override
	void setConnection(Connection con);
	
	void verificarEstadoAverias(Map<String,Object> map) throws BusinessException;
	
	void actualizarEstadoAverias(Map<String,Object> map) throws BusinessException;
	
	void vincularAveriasConFactura(Map<String,Object> map) throws BusinessException;
	
	void updateImporteAveria(Map<String,Object> map) throws BusinessException;
	
	Map<String,Object> selectAveriasOfClient(Map<String,Object> map) throws BusinessException;

	void updateUsadaBono(Map<String, Object> map) throws BusinessException;
}
