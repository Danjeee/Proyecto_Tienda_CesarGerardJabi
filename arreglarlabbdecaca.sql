use tienda_ropa;
SELECT * FROM pedido;
SELECT * FROM linea_pedido;
delete from tienda_ropa.pedido
where numero in(15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30);
SELECT DISTINCT P.numero FROM pedido P WHERE DNI_cliente = '34567890B' and estado = 'En proceso';
delete from linea_pedido
where num_pedido =
any(SELECT P.numero FROM pedido P, linea_pedido L where P.DNI_cliente = "34567890B" and P.numero = L.num_pedido and estado="En proceso");

UPDATE linea_pedido SET cantidad = (SELECT cantidad FROM linea_pedido WHERE num_pedido = 
                                         3  and cod_art =  3)+1 WHERE num_pedido = 3 and cod_art = 3;
                                         select * from linea_pedido where num_pedido=3;