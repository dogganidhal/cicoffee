

CREATE TABLE "products" (
    id UUID NOT NULL PRIMARY KEY ,
    photo_url VARCHAR(4096) NOT NULL,
    name VARCHAR(64) NOT NULL
);


CREATE TABLE "sessions" (
    id UUID NOT NULL PRIMARY KEY ,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    team_id UUID NOT NULL,
    author_id UUID NOT NULL,
    payer_id UUID,
    FOREIGN KEY(team_id) REFERENCES teams(id),
    FOREIGN KEY(author_id) REFERENCES members(id),
    FOREIGN KEY(payer_id) REFERENCES members(id)
);


CREATE TABLE "orders" (
    id UUID NOT NULL PRIMARY KEY ,
    quantity INT NOT NULL,
    session_id UUID NOT NULL,
    FOREIGN KEY(session_id) REFERENCES sessions(id)
);


CREATE TABLE "order_products" (
    order_id UUID NOT NULL,
    product_id UUID NOT NULL,
    FOREIGN KEY(product_id) REFERENCES products(id),
    FOREIGN KEY(order_id) REFERENCES orders(id)
);
