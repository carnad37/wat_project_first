create table order_table
(
    order_id    int          not null comment '주문번호',
    product_id  int          not null comment '제품번호',
    user_id     varchar(300) not null comment '유저아이디',
    amount      int          not null comment '수량',
    total       int          not null comment '금액',
    create_time datetime     not null comment '등록일',
    delete_time datetime     null comment     '삭제일'
);

create table product_table
(
    product_id  int          not null comment '제품번호' primary key,
    `name`      varchar(300) not null comment '제품명',
    price       int          not null comment '가격',
    create_time datetime     not null comment '등록일',
    update_time datetime     null comment '수정일',
    delete_time datetime     null comment '삭제일'
);