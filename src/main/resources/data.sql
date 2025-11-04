DELETE FROM transactions;
DELETE FROM products;
DELETE FROM suppliers;
DELETE FROM categories;
DELETE FROM users;

INSERT INTO users (id, name, email, password, password_hash, phone_number, role, creation_date) VALUES
                                                                                                    (UNHEX(REPLACE('00000000-0000-0000-0000-000000000001','-','')), 'System Admin',      'admin@ims.local',   '$2a$10$Yv8qJqOnuevKfd/lxAx5hOKA9qyMFr8PbLxV8biTdeDfoTfZR1W26',   '$2a$10$Yv8qJqOnuevKfd/lxAx5hOKA9qyMFr8PbLxV8biTdeDfoTfZR1W26',   '+49 151 000000', 'ADMIN',   CURRENT_TIMESTAMP),
                                                                                                    (UNHEX(REPLACE('00000000-0000-0000-0000-000000000002','-','')), 'Inventory Manager', 'manager@ims.local', '$2a$12$G9t0Jt1/VtbNtLS.DSuenuZNdhZqWYKX8eRP7aGuCpSa2jSJu/pMC', '$2a$12$G9t0Jt1/VtbNtLS.DSuenuZNdhZqWYKX8eRP7aGuCpSa2jSJu/pMC', '+49 151 000001', 'MANAGER', CURRENT_TIMESTAMP),
                                                                                                    (UNHEX(REPLACE('00000000-0000-0000-0000-000000000003','-','')), 'Store Keeper',      'keeper@ims.local',  'keeper',  'keeper',  '+49 151 000002', 'MANAGER', CURRENT_TIMESTAMP);

INSERT INTO categories (id, name, creation_date) VALUES
                                                     (UNHEX(REPLACE('11111111-1111-1111-1111-111111111001','-','')), 'Beverages',         CURRENT_TIMESTAMP),
                                                     (UNHEX(REPLACE('11111111-1111-1111-1111-111111111002','-','')), 'Bakery',            CURRENT_TIMESTAMP),
                                                     (UNHEX(REPLACE('11111111-1111-1111-1111-111111111003','-','')), 'Dairy',             CURRENT_TIMESTAMP),
                                                     (UNHEX(REPLACE('11111111-1111-1111-1111-111111111004','-','')), 'Fruits',            CURRENT_TIMESTAMP),
                                                     (UNHEX(REPLACE('11111111-1111-1111-1111-111111111005','-','')), 'Vegetables',        CURRENT_TIMESTAMP),
                                                     (UNHEX(REPLACE('11111111-1111-1111-1111-111111111006','-','')), 'Meat',              CURRENT_TIMESTAMP),
                                                     (UNHEX(REPLACE('11111111-1111-1111-1111-111111111007','-','')), 'Seafood',           CURRENT_TIMESTAMP),
                                                     (UNHEX(REPLACE('11111111-1111-1111-1111-111111111008','-','')), 'Electronics',       CURRENT_TIMESTAMP),
                                                     (UNHEX(REPLACE('11111111-1111-1111-1111-111111111009','-','')), 'Stationery',        CURRENT_TIMESTAMP),
                                                     (UNHEX(REPLACE('11111111-1111-1111-1111-111111111010','-','')), 'Cleaning supplies', CURRENT_TIMESTAMP);

INSERT INTO suppliers (id, name, contact_info, address, creation_date, updated_at) VALUES
                                                                                       (UNHEX(REPLACE('22222222-2222-2222-2222-222222222001','-','')), 'Global Foods GmbH', 'info@globalfoods.de, +49 821 123456', 'Augsburg, Dieselstr. 12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                       (UNHEX(REPLACE('22222222-2222-2222-2222-222222222002','-','')), 'Fresh Farm EU',     'contact@freshfarm.eu, +49 821 555777', 'Augsburg, Hofener Str. 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                       (UNHEX(REPLACE('22222222-2222-2222-2222-222222222003','-','')), 'Tech Import AG',    'sales@techimport.ag, +49 821 99001',  'München, Leopoldstr. 88',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                       (UNHEX(REPLACE('22222222-2222-2222-2222-222222222004','-','')), 'Paper&Co',          'support@paperco.de, +49 821 667788',  'Augsburg, Hermanstr. 25',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                       (UNHEX(REPLACE('22222222-2222-2222-2222-222222222005','-','')), 'CleanPro',          'office@cleanpro.de, +49 821 909090',  'Augsburg, Siemensstr. 3',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (id, name, sku, price, stock_quantity, description, expiry_date, image_url, creation_date, category_id) VALUES
                                                                                                                                 -- Beverages
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333001','-','')), 'Beverages Item 1',  'SKU-1000',  2.50, 20, 'Product for Beverages',        NULL, 'https://unsplash.com/photos/rQRd2sgR0uo/download?force=true&w=390&h=130&fit=crop', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111001','-',''))),
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333002','-','')), 'Beverages Item 2',  'SKU-1001',  3.00, 25, 'Product for Beverages',        NULL, 'https://unsplash.com/photos/kqfBJPmQLUc/download?force=true&w=390&h=130&fit=crop', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111001','-',''))),

                                                                                                                                 -- Bakery
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333003','-','')), 'Bakery Item 1',     'SKU-1002',  1.20, 20, 'Product for Bakery',           NULL, 'https://unsplash.com/photos/6uEJBFcZlto/download?force=true&w=390&h=130&fit=crop', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111002','-',''))),
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333004','-','')), 'Bakery Item 2',     'SKU-1003',  1.70, 25, 'Product for Bakery',           NULL, 'https://unsplash.com/photos/52SdSm_zxwU/download?force=true&w=390&h=130&fit=crop', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111002','-',''))),

                                                                                                                                 -- Dairy
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333005','-','')), 'Dairy Item 1',      'SKU-1004',  1.75, 20, 'Product for Dairy',            NULL, 'https://unsplash.com/photos/kPqaqug998Y/download?force=true&w=390&h=130&fit=crop', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111003','-',''))),
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333006','-','')), 'Dairy Item 2',      'SKU-1005',  2.25, 25, 'Product for Dairy',            NULL, 'https://unsplash.com/photos/J55Xn5F-w9w/download?force=true&w=390&h=130&fit=crop', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111003','-',''))),

                                                                                                                                 -- Fruits
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333007','-','')), 'Fruits Item 1',     'SKU-1006',  3.00, 20, 'Product for Fruits',           NULL, 'https://unsplash.com/photos/e1g5SVowPlw/download?force=true&w=390&h=130&fit=crop', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111004','-',''))),
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333008','-','')), 'Fruits Item 2',     'SKU-1007',  3.50, 25, 'Product for Fruits',           NULL, 'https://unsplash.com/photos/9HbL0mGRpL8/download?force=true&w=390&h=130&fit=crop', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111004','-',''))),

                                                                                                                                 -- Vegetables
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333009','-','')), 'Vegetables Item 1', 'SKU-1008',  2.20, 20, 'Product for Vegetables',       NULL, 'https://i.pinimg.com/736x/52/f4/b3/52f4b300d1249c63a72ee0f97038ef99.jpg', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111005','-',''))),
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333010','-','')), 'Vegetables Item 2', 'SKU-1009',  2.70, 25, 'Product for Vegetables',       NULL, 'https://unsplash.com/photos/m0tdvKoq0CU/download?force=true&w=390&h=130&fit=crop', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111005','-',''))),

                                                                                                                                 -- Meat
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333011','-','')), 'Meat Item 1',       'SKU-1010',  9.80, 20, 'Product for Meat',             NULL, 'https://unsplash.com/photos/STlZqfOGT7k/download?force=true&w=390&h=130&fit=crop', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111006','-',''))),

                                                                                                                                 -- Seafood
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333012','-','')), 'Seafood Item 1',    'SKU-1011', 12.50, 20, 'Product for Seafood',          NULL, 'https://unsplash.com/photos/fV3zTanbO80/download?force=true&w=390&h=130&fit=crop', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111007','-',''))),

                                                                                                                                 -- Electronics
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333013','-','')), 'Electronics Item 1','SKU-1012', 49.99, 20, 'Product for Electronics',      NULL, 'https://unsplash.com/photos/O0lF33aYKpI/download?force=true&w=390&h=130&fit=crop', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111008','-',''))),

                                                                                                                                 -- Stationery
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333014','-','')), 'Stationery Item 1', 'SKU-1013',  5.30, 20, 'Product for Stationery',       NULL, 'https://sendico.com/storage/blog_post/1724310952_Essential%20Japanese%20Stationery%20for%20Students%20and%20Professionals.webp', CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111009','-',''))),

                                                                                                                                 -- Cleaning supplies
                                                                                                                                 (UNHEX(REPLACE('33333333-3333-3333-3333-333333333015','-','')), 'Cleaning supplies Item 1','SKU-1014',4.40, 20, 'Product for Cleaning supplies',NULL, 'https://unsplash.com/photos/9gzU1mtTzWM/download?force=true&w=390&h=130&fit=crop',  CURRENT_TIMESTAMP, UNHEX(REPLACE('11111111-1111-1111-1111-111111111010','-','')));
INSERT INTO transactions
(id, total_products, total_price, transaction_type, status, description, note,
 creation_date, updated_at, product_id, user_id, supplier_id)
VALUES
    (UNHEX(REPLACE('44444444-4444-4444-4444-444444444001','-','')),
     10, 25.00, 'SALE', 'COMPLETED',
     'SALE of 10 pcs', 'auto tx 1',
     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     UNHEX(REPLACE('33333333-3333-3333-3333-333333333002','-','')),
     UNHEX(REPLACE('00000000-0000-0000-0000-000000000001','-','')),
     UNHEX(REPLACE('22222222-2222-2222-2222-222222222001','-',''))),

    (UNHEX(REPLACE('44444444-4444-4444-4444-444444444002','-','')),
     4, 12.00, 'PURCHASE', 'PENDING',
     'PURCHASE of 4 pcs', 'auto tx 2',
     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     UNHEX(REPLACE('33333333-3333-3333-3333-333333333003','-','')),
     UNHEX(REPLACE('00000000-0000-0000-0000-000000000002','-','')),
     UNHEX(REPLACE('22222222-2222-2222-2222-222222222002','-',''))),

    (UNHEX(REPLACE('44444444-4444-4444-4444-444444444003','-','')),
     11, 33.00, 'PURCHASE', 'COMPLETED',
     'PURCHASE of 11 pcs', 'auto tx 3',
     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     UNHEX(REPLACE('33333333-3333-3333-3333-333333333007','-','')),
     UNHEX(REPLACE('00000000-0000-0000-0000-000000000003','-','')),
     UNHEX(REPLACE('22222222-2222-2222-2222-222222222003','-',''))),

    (UNHEX(REPLACE('44444444-4444-4444-4444-444444444004','-','')),
     6, 59.94, 'SALE', 'PENDING',
     'SALE of 6 pcs', 'auto tx 4',
     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     UNHEX(REPLACE('33333333-3333-3333-3333-333333333013','-','')),
     UNHEX(REPLACE('00000000-0000-0000-0000-000000000001','-','')),
     UNHEX(REPLACE('22222222-2222-2222-2222-222222222001','-',''))),

    (UNHEX(REPLACE('44444444-4444-4444-4444-444444444005','-','')),
     13, 28.60, 'PURCHASE', 'COMPLETED',
     'stock correction 13 pcs', 'auto tx 5',
     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     UNHEX(REPLACE('33333333-3333-3333-3333-333333333009','-','')),
     UNHEX(REPLACE('00000000-0000-0000-0000-000000000002','-','')),
     UNHEX(REPLACE('22222222-2222-2222-2222-222222222001','-',''))),

    (UNHEX(REPLACE('44444444-4444-4444-4444-444444444006','-','')),
     3, 29.40, 'SALE', 'PENDING',
     'SALE of 3 pcs', 'auto tx 6',
     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     UNHEX(REPLACE('33333333-3333-3333-3333-333333333011','-','')),
     UNHEX(REPLACE('00000000-0000-0000-0000-000000000003','-','')),
     UNHEX(REPLACE('22222222-2222-2222-2222-222222222005','-',''))),

    (UNHEX(REPLACE('44444444-4444-4444-4444-444444444007','-','')),
     14, 70.00, 'SALE', 'COMPLETED',
     'SALE of 14 pcs', 'auto tx 7',
     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     UNHEX(REPLACE('33333333-3333-3333-3333-333333333001','-','')),
     UNHEX(REPLACE('00000000-0000-0000-0000-000000000002','-','')),
     UNHEX(REPLACE('22222222-2222-2222-2222-222222222001','-',''))),

    (UNHEX(REPLACE('44444444-4444-4444-4444-444444444008','-','')),
     8, 20.00, 'PURCHASE', 'PENDING',
     'PURCHASE of 8 pcs', 'auto tx 8',
     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     UNHEX(REPLACE('33333333-3333-3333-3333-333333333014','-','')),
     UNHEX(REPLACE('00000000-0000-0000-0000-000000000003','-','')),
     UNHEX(REPLACE('22222222-2222-2222-2222-222222222002','-',''))),

    (UNHEX(REPLACE('44444444-4444-4444-4444-444444444009','-','')),
     9, 39.60, 'SALE', 'COMPLETED',
     'SALE of 9 pcs', 'auto tx 9',
     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     UNHEX(REPLACE('33333333-3333-3333-3333-333333333015','-','')),
     UNHEX(REPLACE('00000000-0000-0000-0000-000000000001','-','')),
     UNHEX(REPLACE('22222222-2222-2222-2222-222222222001','-',''))),

    (UNHEX(REPLACE('44444444-4444-4444-4444-444444444010','-','')),
     7, 87.50, 'PURCHASE', 'COMPLETED',
     'PURCHASE of 7 pcs', 'auto tx 10',
     CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
     UNHEX(REPLACE('33333333-3333-3333-3333-333333333012','-','')),
     UNHEX(REPLACE('00000000-0000-0000-0000-000000000002','-','')),
     UNHEX(REPLACE('22222222-2222-2222-2222-222222222001','-','')));

ALTER TABLE transactions DROP FOREIGN KEY FKcdpkn7bkq15bjvlw9mo46l9ft;

ALTER TABLE transactions
    ADD CONSTRAINT fk_transactions_product
        FOREIGN KEY (product_id) REFERENCES products(id)
            ON DELETE CASCADE;

ALTER TABLE transactions DROP FOREIGN KEY FKih7q8ly56miqunee4xnylu4i9;

ALTER TABLE transactions
    ADD CONSTRAINT fk_transactions_supplier
        FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
            ON DELETE CASCADE;