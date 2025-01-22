package mocking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.time.LocalDate;

public class SpartanRepositoryTests {
    SpartanRepository sut = new SpartanRepository();
    Spartan mock1 = Mockito.mock(Spartan.class);
    Spartan mock2 = Mockito.mock(Spartan.class);

    @BeforeEach
    void setUp() {
        sut.addSpartan(mock1);
        sut.addSpartan(mock2);
    }
    @Test
    @DisplayName("Testing add spartan")
    void testAddSpartan() {
        Assertions.assertEquals(2, sut.getNumSpartans());
    }

    @Test
    @DisplayName("Testing findSpartan")
    void testFindSpartan() {
        Mockito.when(mock2.getId())
                .thenReturn(1);
        Assertions.assertEquals(mock2, sut.findSpartan(1));
    }
    @Test
    @DisplayName("Testing getSpartansCreatedLast24Hours")
    public void testGetSpartansCreatedLast24Hours() {
        Mockito.when(mock1.getStartDate())
                .thenReturn(LocalDate.now());
        Mockito.when(mock2.getStartDate())
                .thenReturn(
                        LocalDate.now().minusDays(2)
                );
        Assertions.assertEquals(1, sut.getSpartansCreatedLast24Hours().size());
    }

    @Test
    public void testChangingName() {
        Mockito.when(mock1.getId()).thenReturn(3);
        Mockito.doThrow(IllegalArgumentException.class)
                .when(mock1)
                .setName(Mockito.any(String.class));

        Assertions.assertFalse(sut.changeName(3, "Doris"));
    }
    @Test
    @DisplayName("Check getId is called once per Spartan")
    public void checkGetIdCalledOnce() {
        sut.findSpartan(20);
        Mockito.verify(mock1, Mockito.times(1)).getId();
        Mockito.verify(mock2, Mockito.times(1)).getId();
    }
    @Test
    @DisplayName("Check methods are called in order")
    public void checkOrder() {
        sut.getAllSpartans();

        InOrder inOrder = Mockito.inOrder(mock2);
        inOrder.verify(mock2).getId();
        inOrder.verify(mock2).getName();
    }
    @Test
    @DisplayName("test correct parameter")
    public void testMethodIsCalledWithCorrectParameter() {
        Mockito.when(mock1.getId()).thenReturn(3);
        sut.changeName(3, "Cathy");
        Mockito.verify(mock1).setName("Cathy");
        //Mockito.verifyNoMoreInteractions(mock1);
    }
}
