-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------
             
-- 특정 상품 하나 가져오기 [ getSelectOneProduct ]
select * from product where pdID = ?;

-- 특정 카테고리 이름 상품 리스트 가져오기 [ getCategoryByProductList ]
select * from product where product.pdID in ( select pdID from category where cgName like ? );
             
--  특정 카테고리 타입 리스트 가져오기 [ getCategoryTypeList ]
select distinct cgType from category where cgName = ?;

-- 특정 상품의 정보 하나를 가져오기 [ selectOne ]
select * from ? where pdID like ( select pdID from product where pdID like ? );

-- 특정 상품 가져오기 [ getProductID ]
select pdID from product where pdName like %?%;

-- ------------------------------------------------------------------------------------------------------------
-- ------------------------------------------------------------------------------------------------------------

select *
from product
where pdID in (
select pdID
from category
where cgType like '%자켓%');