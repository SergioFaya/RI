package uo.ri.conf.properties;

public class Queries {

	//DeleteMechanic
	public final static String SQL_DELETE_FROM_MECANICOS = "delete from TMecanicos where id = ?";
	//CreateInvoiceFor
	public static final String SQL_IMPORTE_REPUESTOS = "select sum(s.cantidad * r.precio) "
			+ "	from  TSustituciones s, TRepuestos r " + "	where s.repuesto_id = r.id "
			+ "		and s.intervencion_averia_id = ?";

	public static final String SQL_IMPORTE_MANO_OBRA = "select sum(i.minutos * tv.precioHora / 60) "
			+ "	from TAverias a, TIntervenciones i, TVehiculos v, TTiposVehiculo tv" + "	where i.averia_id = a.id "
			+ "		and a.vehiculo_id = v.id" + "		and v.tipo_id = tv.id" + "		and a.id = ?"
			+ "		and a.status = 'TERMINADA'";

	public static final String SQL_UPDATE_IMPORTE_AVERIA = "update TAverias set importe = ? where id = ?";

	public static final String SQL_ULTIMO_NUMERO_FACTURA = "select max(numero) from TFacturas";

	public static final String SQL_INSERTAR_FACTURA = "insert into TFacturas(numero, fecha, iva, importe, status) "
			+ "	values(?, ?, ?, ?, ?)";

	public static final String SQL_VINCULAR_AVERIA_FACTURA = "update TAverias set factura_id = ? where id = ?";

	public static final String SQL_ACTUALIZAR_ESTADO_AVERIA = "update TAverias set status = ? where id = ?";

	public static final String SQL_VERIFICAR_ESTADO_AVERIA = "select status from TAverias where id = ?";

	public static final String SQL_RECUPERAR_CLAVE_GENERADA = "select id from TFacturas where numero = ?";

	//AddMechanic
	public final static String SQL_INSERT_INTO_MECANICOS = "insert into TMecanicos(nombre, apellidos) values (?, ?)";
	//UpdateMechanic
	public final static String SQL_UPDATE_MECANICOS = "update TMecanicos " + "set nombre = ?, apellidos = ? " + "where id = ?";

	//FindAllMechanic
	public final static String SELECT_ALL_FROM_MECANICOS = "select id, nombre, apellidos from TMecanicos";
}
