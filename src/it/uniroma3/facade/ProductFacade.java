package it.uniroma3.facade;

import it.uniroma3.model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "productController")
public class ProductFacade {

	@PersistenceContext(unitName = "products-unit")
	private EntityManager em;

	public ProductFacade() {
	}

	public Product createProduct(String name, Float price, String code, String description, int inStock) {
		Product product = new Product(name, price, description, code, inStock);
		this.em.persist(product);
		return product;
	}

	public Product getProduct(Long id) {
		return this.em.find(Product.class, id);
	}

	public List<Product> getAllProducts() {
		return this.em.createQuery("SELECT p FROM Product p",
				Product.class).getResultList();
	}


	public List<Product> searchProduct(String s) {
		String l = "";
		try {
			Long.parseLong(s);
			l = "or p.id = " + s;
		} catch (Exception e) {
		}
		s = s.toUpperCase();
		return this.em.createQuery
				("select p from Product p where UPPER(p.name) like '%" + s + "%' " +
						"or UPPER(p.description) like '%" + s + "%' " +
						"or UPPER(p.code) like '%" + s + "%'" + l,
						Product.class).getResultList();
	}

	public void updateProduct(Product product) {
		this.em.merge(product);
	}

	public void deleteProduct(Long id) {
		Product product = getProduct(id);
		if (product != null) {
			this.em.remove(product);
		}
	}
}
