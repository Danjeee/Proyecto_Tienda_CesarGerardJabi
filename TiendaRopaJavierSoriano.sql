delimiter $$
CREATE TRIGGER addPedido after insert on pedido for each row
begin
	update cliente
	set num_pedidos = num_pedidos+1
	where cliente.DNI = NEW.DNI_cliente;
end
$$
CREATE TRIGGER removePedido after update on pedido for each row
begin
	if NEW.estado = "Cancelado"
	then
		update cliente
		set num_pedidos = num_pedidos-1
        where cliente.DNI = NEW.DNI_cliente;
	end if;
end
