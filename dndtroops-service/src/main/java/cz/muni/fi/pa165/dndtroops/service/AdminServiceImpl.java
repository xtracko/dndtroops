package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.dao.AdministratorDao;
import cz.muni.fi.pa165.dndtroops.entities.Administrator;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * @author Miroslav Macor
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdministratorDao administratorDao;

    @Autowired
    private HeroService heroService;

    @Override
    public void createAdministrator(Administrator admin,String password) {
        admin.setPasswordHash(createHash(password));
        administratorDao.createAdministrator(admin);
        
    }

    @Override
    public void updateAdministrator(Administrator admin) {
        administratorDao.updateAdministrator(admin);
    }

    @Override
    public Administrator findAdministatorById(Long id) { return administratorDao.findAdministatorById(id); }

    @Override
    public Administrator findAdministratorByName(String name) { return administratorDao.findAdministratorByName(name);
    }

    @Override
    public List<Administrator> findAllAdministrators() {return administratorDao.findAllAdministrators();}

    @Override
    public void removeAdministrator(Administrator admin) {administratorDao.removeAdministrator(admin);}

    @Override
    public Troop complateMissionForTroop(Troop troop, int xpGained, int goldGained, String newMission){
        List<Hero> heroes = heroService.getHeroesByTroop(troop);
        for(Hero hero: heroes){
            hero.setXp(hero.getXp() + xpGained / heroes.size());
        }
        troop.setGoldenMoney(troop.getGoldenMoney() + goldGained);
        troop.setMission(newMission);
        return troop;
    }

    @Override
    public Troop createNewParty(List<Hero> heroes, String mission, String name, int pulledGold){
        Troop troop =  new Troop(name,mission,pulledGold);
        for (Hero hero: heroes){
            hero.setTroop(troop);
            hero.setHealth(100);
        }
        return troop;
    }

    @Override
    public boolean authenticate(Administrator admin, String password) {
        return validatePassword(password, admin.getPasswordHash());
    }

    @Override
    public boolean isAdmin(Administrator admin) {
        return  findAdministatorById(admin.getId()).isIsAdmin();
    }

    
    //see  https://crackstation.net/hashing-security.htm#javasourcecode
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }
}
