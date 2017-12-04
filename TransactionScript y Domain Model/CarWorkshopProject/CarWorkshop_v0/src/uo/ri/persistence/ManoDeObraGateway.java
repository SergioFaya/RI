package uo.ri.persistence;

import java.sql.Connection;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ManoDeObraGateway {
	
	Map<String,Object> selectImporteManoDeObra(Map<String,Object> map) throws BusinessException;

	void setConnection(Connection con);
}
