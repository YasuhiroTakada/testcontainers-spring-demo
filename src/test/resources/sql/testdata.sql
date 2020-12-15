insert into author(id, name)
values
('1e0aea5dc4c0460cb9447ac35599b8a3', 'Joe'),
('d008442a52ae419db759135d52f5fab1', 'Donald');

insert into blog_post(id, category, content, like_count, title, author_id)
values
('eb6800cfa7ef4e30bd85055bb678ddf4', 'Java', '{"content":"blog content..."}', 100, 'Java guide', '1e0aea5dc4c0460cb9447ac35599b8a3'),
('e28303879715444cbfc3c3f20fa75c96', 'Java', '{"content":"blog content..."}', 200, 'Java Puzzle', '1e0aea5dc4c0460cb9447ac35599b8a3'),
('dfb9fafe8e944cb3888526013acf0266', 'Java', '{"content":"blog content..."}', 300, 'Java Testing', '1e0aea5dc4c0460cb9447ac35599b8a3'),
('a0c3b766f5f94893af389979fae5a1e6', 'Java', '{"content":"blog content..."}', 400, 'Java Professional', '1e0aea5dc4c0460cb9447ac35599b8a3')
;