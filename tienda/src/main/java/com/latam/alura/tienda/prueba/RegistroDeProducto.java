package com.latam.alura.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.latam.alura.tienda.dao.CategoriaDAO;
import com.latam.alura.tienda.dao.ProductoDAO;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAutils;

public class RegistroDeProducto {

	public static void main(String[] args) {
		
		registrarProducto();
		
		EntityManager em = JPAutils.getEntityManager();
		ProductoDAO productoDao = new ProductoDAO(em);
		
		Producto producto = productoDao.consultaPorId(1l);
		
		System.out.println(producto.getNombre());
		
		List<Producto> productos = productoDao.consultaPorNombreDeCategoria("CELULARES");
		BigDecimal precio = productoDao.consultarPrecioPorNombreDeProducto("Xiaomi Redmi");
		
		System.out.println(precio);
		productos.forEach(prod -> System.out.println(prod.getDescripcion()));
	}

	private static void registrarProducto() {
		Categoria celulares = new Categoria("CELULARES");
		
		Producto celular = new Producto("Xiaomi Redmi", "Muy bueno", new BigDecimal("800"), celulares);
		
		EntityManager em = JPAutils.getEntityManager();
		ProductoDAO productoDao = new ProductoDAO(em);
		CategoriaDAO categoriaDao = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDao.guardar(celulares);
		productoDao.guardar(celular);
		
		em.getTransaction().commit();
		em.close();
	}

}
