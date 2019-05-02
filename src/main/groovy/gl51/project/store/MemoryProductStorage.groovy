package gl51.project.store


class MemoryProductStorage implements  ProductStorage {
	private Map<String, Product> productmap = [:]
    @Override
    void save(Product p) {
		productmap[p.name] = p
    }

    @Override
    void update(String id, Product p) {

    }

    @Override
    Product getByID(String id) {
        return null
    }

    @Override
    void delete(String id) {

    }

    @Override
    List<Product> all() {
		List<Product> list_result = []
		for (e in productmap) {
			list_result.add(e.value)
			
		}
		print(productmap.size())
		return list_result
    }
}
