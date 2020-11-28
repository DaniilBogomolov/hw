package ru.itis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.models.*;
import ru.itis.models.Order.OrderStatus;
import ru.itis.services.OrderService;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @BeforeEach
    public void init() {
        
        when(orderService.updateStatus(18L, OrderStatus.READY)).thenReturn(readyOrder());
        when(orderService.updateStatus(-1L, OrderStatus.ACCEPTED)).thenThrow(IllegalArgumentException.class);
        when(orderService.updateStatus(18L, OrderStatus.ACCEPTED)).thenThrow(IllegalStateException.class);
    }

    @Test
    public void testOrderAcceptedUpdate() throws Exception {
        Order order = readyOrder();
        mockMvc.perform(put("/orders/18/update/READY")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(order.getPrice()))
                .andExpect(jsonPath("$.status").value(order.getStatus().toString()))
                .andDo(document("update_order", responseFields(
                        fieldWithPath("id").description("Order id"),
                        fieldWithPath("price").description("Order price"),
                        fieldWithPath("status").description("Current order status"),
                        fieldWithPath("orderDate").description("Time the order was created"),
                        subsectionWithPath("orderer").description("Orderer info json"),
                        subsectionWithPath("details").description("Order details json")
                )));
    }

    @Test
    public void testNonExistingOrder() throws Exception {
        mockMvc.perform(put("/orders/-1/update/ACCEPTED")).andDo(print())
                .andExpect(status().is4xxClientError())
                .andDo(document("update_non_existing_order"));
    }

    @Test
    public void testUpdateWithBadStatus() throws Exception {
        mockMvc.perform(put("/orders/18/update/ACCEPTED")).andDo(print())
                .andExpect(status().is4xxClientError())
                .andDo(document("update_invalid_status"));
    }

    private Order readyOrder() {
        Tobacco tobacco = Tobacco.builder()
                .id(1L)
                .flavour("Nuts")
                .manufacturer("DarkSide")
                .price(325)
                .status(GoodsStatus.IN_STOCK)
                .build();
        Tobacco tobacco1 = Tobacco.builder()
                .id(2L)
                .flavour("Apple")
                .price(500)
                .manufacturer("Nahla")
                .status(GoodsStatus.IN_STOCK)
                .build();
        Bowl bowl = Bowl.builder()
                .id(1L)
                .description("Standart phunnel")
                .price(450)
                .status(GoodsStatus.IN_STOCK)
                .type(Bowl.BowlType.PHUNNEL)
                .build();
        ShishaWaterJar waterJar = ShishaWaterJar.builder()
                .id(1L)
                .description("Pure glass")
                .manufacturer("JarsCompany")
                .status(GoodsStatus.IN_STOCK)
                .price(600)
                .build();
        Hookah hookah = Hookah.builder()
                .id(1L)
                .waterJar(waterJar)
                .status(GoodsStatus.SOLD)
                .price(4000)
                .manufacturer("Russia")
                .description("Original stick")
                .bowl(bowl)
                .build();
        OrderDetails details = OrderDetails.builder()
                .id(1L)
                .hookahs(List.of(hookah))
                .tobaccos(List.of(tobacco, tobacco1))
                .bowls(EMPTY_LIST)
                .waterJars(EMPTY_LIST)
                .build();

        return Order.builder()
                .id(18L)
                .price(3075)
                .orderDate(LocalDate.of(2020, 10, 22))
                .status(OrderStatus.READY)
                .details(details)
                .orderer(Customer.builder()
                        .id(1L)
                        .build())
                .build();
    }
}
