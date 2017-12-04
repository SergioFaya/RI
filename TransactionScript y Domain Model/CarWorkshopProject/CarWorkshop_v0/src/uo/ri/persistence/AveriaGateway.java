package uo.ri.persistence;

import java.sql.Connection;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface AveriaGateway {
	
	void setConnection(Connection con);
	
	void verificarEstadoAverias(Map<String,Object> map) throws BusinessException;
	
	void actualizarEstadoAverias(Map<String,Object> map) throws BusinessException;
	
	void vincularAveriasConFactura(Map<String,Object> map) throws BusinessException;
	
	void updateImporteAveria(Map<String,Object> map) throws BusinessException;
}
