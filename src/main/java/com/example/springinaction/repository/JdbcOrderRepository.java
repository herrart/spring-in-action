package com.example.springinaction.repository;

import com.example.springinaction.model.Dish;
import com.example.springinaction.model.Ingredient;
import com.example.springinaction.model.Order;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Order save(Order order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                """
                            INSERT INTO orders (delivery_name,
                            delivery_street,
                            delivery_city,
                            delivery_state,
                            delivery_zip,
                            cc_number,
                            cc_expiration,
                            cc_cvv,
                            placed_at) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP);

        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getPlacedAt()));

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, generatedKeyHolder);
        order.setId(Long.valueOf((Integer)generatedKeyHolder.getKeys().get("id")));

        List<Dish> dishes = order.getDishes();
        for (Dish dish : dishes) {
            saveDish(order.getId(), dish);
        }

        return order;
    }

    private void saveDish(Long orderId, Dish dish) {
        dish.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                """
                            INSERT INTO dish (
                            name,
                            order_id,
                            created_at) VALUES (?, ?, ? )
                        """,
                Types.VARCHAR, Type.LONG, Types.TIMESTAMP);

        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        dish.getName(),
                        orderId,
                        dish.getCreatedAt()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);
        dish.setId(Long.valueOf((Integer)keyHolder.getKeys().get("id")));

        for (Ingredient ingredient : dish.getIngredients()) {
            jdbcTemplate.update("INSERT INTO dish_composition (dish_id, ingredient_id) VALUES (?, ?)",
                    dish.getId(), ingredient.getId());
        }

    }
}
