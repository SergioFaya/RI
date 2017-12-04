package uo.ri.persistence;

import java.sql.Connection;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ManoDeObraGateway extends Gateway{
	
	Map<String,Object> selectImporteManoDeObra(Map<String,Object> map) throws BusinessException;

	@Override
	void setConnection(Connection con);
}
