ALTER TABLE transactions ADD FOREIGN KEY (category_id) REFERENCES categories(id);