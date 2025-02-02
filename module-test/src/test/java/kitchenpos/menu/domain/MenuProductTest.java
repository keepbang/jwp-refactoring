package kitchenpos.menu.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.Arrays;

import static kitchenpos.product.domain.ProductTest.후라이드_상품;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuProductTest {
    public static final MenuProduct 후라이드 = new MenuProduct(1L, 1L, 1);
    public static final MenuProduct 양념치킨 = new MenuProduct(2L, 2L, 1);

    @Test
    @DisplayName("메뉴 상품 생성")
    void create() {
        // given
        // when
        MenuProduct actual = new MenuProduct(1L, 1L, 1);

        // then
        assertThat(actual).isEqualTo(후라이드);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "32000,2", "16000,1", "0,0"
    })
    @DisplayName("메뉴 상품 가격 * 상품 개수")
    void totalPriceTest(Long totalPrice, long quantity) {
        // given
        // when
        MenuProduct actual = new MenuProduct(1L, 후라이드_상품.getId(), quantity);
        // then
        assertThat(actual.totalPrice(Arrays.asList(후라이드_상품))).isEqualTo(BigDecimal.valueOf(totalPrice));
    }
}
