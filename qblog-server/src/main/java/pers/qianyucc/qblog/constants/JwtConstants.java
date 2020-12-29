package pers.qianyucc.qblog.constants;

public class JwtConstants {
    public static final String ROLE_CLAIMS = "role";
    /**
     * 过期时间：24小时
     */
    public static final long EXPIRATION = 60 * 60*24L;
    /**
     * 私钥
     */
    public static final String JWT_SECRET_KEY = "uED44b4NzOQlodczbRDMvbtjDMNCiS7BUpe39Mcq";
    /**
     * JWT 前缀
     */
    public static final String TOKEN_PREFIX = "zhulinbimo_";
    /**
     * token 类型
     */
    public static final String TOKEN_TYPE = "JWT";
    /**
     * 签发者
     */
    public static final String TOKEN_ISSUER = "QianYu";
    /**
     * 加在响应头的哪个字段
     */
    public static final String TOKEN_HEADER = "Authorization";
    /**
     * 在 Redis 中存储的key
     */
    public static final String REDIS_KEY = "jwt_token";
}
