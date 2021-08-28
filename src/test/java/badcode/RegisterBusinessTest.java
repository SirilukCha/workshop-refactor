package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class setSpeaker implements SpeakerRepository {

    @Override
    public Integer saveSpeaker(Speaker speaker) {
        return 1000;
    }
}

public class RegisterBusinessTest {
    // test to pass
    // test to fail


    @Test
    @DisplayName("ไม่มีชื่อ จะเกิด exception first name id required.")
    public void case01() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            registerBusiness.register(null, new Speaker());
            fail();
        } catch (ArgumentNullException e) {
        }
    }

    @Test
    @DisplayName("มีชื่อ แต่ empty จะเกิด exception first name id required.")
    public void case02() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("");
            registerBusiness.register(null, speaker);
            fail();
        } catch (ArgumentNullException e) {
        }
    }

    @Test
    @DisplayName("มีชื่อ ไม่มีนามสกุล จะเกิด exception last name id required.")
    public void case03() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("siriluk");
            registerBusiness.register(null, speaker);
            fail();
        } catch (ArgumentNullException e) {
        }
    }

    @Test
    @DisplayName("มีชื่อ แต่ นามสกุล empty จะเกิด exception last name id required.")
    public void case04() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("siriluk");
            speaker.setLastName("");
            registerBusiness.register(null, speaker);
            fail();
        } catch (ArgumentNullException e) {
        }
    }

    @Test
    @DisplayName("มีชื่อ-นามสกุล แต่ไม่มี email จะเกิด exception email id required.")
    public void case05() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("siriluk");
            speaker.setLastName("cha");
            registerBusiness.register(null, speaker);
            fail();
        } catch (ArgumentNullException e) {
        }
    }

    @Test
    @DisplayName("มีชื่อ-นามสกุล แต่ email empty จะเกิด exception email id required.")
    public void case06() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("siriluk");
            speaker.setLastName("cha");
            speaker.setEmail("");
            registerBusiness.register(null, speaker);
            fail();
        } catch (ArgumentNullException e) {
        }
    }

    @Test
    @DisplayName("domain ไม่ถูกต้อง format จะเกิด Speaker doesn't meet our standard rules.")
    public void case07() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("siriluk");
            speaker.setLastName("cha");
            speaker.setEmail("siriluk@test.com");
            registerBusiness.register(null, speaker);
            fail();
        } catch (SpeakerDoesntMeetRequirementsException e) {
        }
    }

    @Test
    @DisplayName("domain ไม่ถูกต้อง length จะเกิด Can't save a speaker.")
    public void case08() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("siriluk");
            speaker.setLastName("cha");
            speaker.setEmail("siriluk@gmail.com@test");
            registerBusiness.register(null, speaker);
            fail();
        } catch (DomainEmailInvalidException e) {
        }
    }

    @Test
    @DisplayName("domain ไม่ถูกต้อง จะเกิด Can't save a speaker.")
    public void case09() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        try {
            Speaker speaker = new Speaker();
            speaker.setFirstName("siriluk");
            speaker.setLastName("cha");
            speaker.setEmail("siriluk@gmail.com");
            registerBusiness.register(null, speaker);
            fail();
        } catch (SaveSpeakerException e) {
        }
    }

    @Test
    @DisplayName("get fee experienceYear <= 3")
    public void case10() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        int fee = registerBusiness.getFee(3);
        assertEquals(250, fee);
    }

    @Test
    @DisplayName("get fee experienceYear <= 5")
    public void case11() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        int fee = registerBusiness.getFee(5);
        assertEquals(100, fee);
    }

    @Test
    @DisplayName("get fee experienceYear <= 9")
    public void case12() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        int fee = registerBusiness.getFee(9);
        assertEquals(50, fee);
    }

    @Test
    @DisplayName("get fee experienceYear <= 1")
    public void case13() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        int fee = registerBusiness.getFee(1);
        assertEquals(500, fee);
    }

    @Test
    @DisplayName("get fee else")
    public void case14() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        int fee = registerBusiness.getFee(10);
        assertEquals(0, fee);
    }

    @Test
    @DisplayName("pass all")
    public void case15() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("siriluk");
        speaker.setLastName("cha");
        speaker.setEmail("siriluk@gmail.com");
        speaker.setExp(3);
        int speakerId = registerBusiness.register(new setSpeaker(), speaker);
        assertEquals(1000, speakerId);
    }
}
