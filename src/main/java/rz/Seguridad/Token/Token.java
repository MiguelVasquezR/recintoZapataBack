package rz.Seguridad.Token;

import com.google.gson.JsonObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;
import rz.Modelo.Usuario;

import java.util.Date;

import javax.crypto.SecretKey;

public class Token {
    static String secretKey = "PruebaUltraSecreta120901dsalkjlds";

    public static String generateJwt(Usuario usuario) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis + (7 * 24 * 60 * 60 * 1000));
        String token = Jwts.builder()
                .issuedAt(now)
                .claim("ID", usuario.getId_persona())
                .claim("ROL", usuario.getRol())
                .expiration(exp)
                .signWith(getKey(secretKey), Jwts.SIG.HS256)
                .compact();
        return token;
    }

    public static Claims verifyJwt(String token) {
        return Jwts.parser()
                .verifyWith(getKey(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static JsonObject validarToken(String token){
        JsonObject jsonObject = new JsonObject();
        try{
            Claims claims = verifyJwt(token);
            jsonObject.addProperty("ID", claims.get("ID").toString());
            jsonObject.addProperty("Rol", claims.get("ROL").toString());
            return jsonObject;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static SecretKey getKey(String key) {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

}
