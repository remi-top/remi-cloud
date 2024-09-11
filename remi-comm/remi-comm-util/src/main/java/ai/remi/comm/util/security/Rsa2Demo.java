package ai.remi.comm.util.security;

/**
 * @author DianJiu 【公众号 点九开源】
 * @email lidianjiu@njydsz.com
 * @date 2022-07-26
 * @desc 公钥加密，私钥解密；私钥加签，公钥验签。
 */
public class Rsa2Demo {

    public static void main(String[] args) {
        mookHttp();
    }

    public static void mookHttp(){
        Rsa2Utils.initRSAKey(2048);
        // 初始化A公司的公钥、私钥
        String privateKeyA= "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC1YbT3O4Rd2F5d7R/+61clUBvGtpnYYzDsUCmVLCYnE1zCNQawi5ga+cpdgyafOLFJcVGfcHEushXgJ3XFp0mriRCKnBiA0OwoNa0sVZyW46eLsYhxx6aNmcsfk04xUNR52FYRD3gvLpfIXHlW4Zib9FtYJThjXo6kSLrZbh2f9gaYNlJzJn8tWljI3sPgzgvvVCtPbwFOW5D7Pt7n/nsL3AUM5ZqItRBvlkejitbdjYlg2WXJA6pDYtAxO9+iwiDsftQqF8QzXiZDTBEr+jqKPk3ZpYpuE8nXSWpx/1c4ujhI6R+/CVvj4PEccfDs9x2dusVLgVdYgXxFY4jyyFhrAgMBAAECggEBAJdxCAaw89jjMg9cUC2o0289DqQAjD1GvG9ikwWRAh34JaelXHuFYi+LxjIDsfiY/jVi3xKvcGDbojYw3D12aqVFYMFKiY7sYXFFN9zfcknttFOHnHPbja2G7jjj5hJ4+Z7uSRYfg2ppjaNlyo4G+EOmZ6oWEZmuNozmiNPIBAYsmNXmxK7jmFKsMr8dN8oTraP2j5rjpWwvcoqoJKacGLY8dV8/miGlaTyJzrJT/JRbvPl1pCe9W5MMv2XhwaIrpM2kPuO1HQjBLgsFXQyYxAP0BvqID7QX+MYK104VcpuuAARLqZZUwEZP2quJUMxT973KUlLkBUdgfrLoElvNXOECgYEA/1x4botRAudqjaWH4UTAqaCy0/WrWluZqGxaZn/aS3BdFYya/zMH8kY6s16yKZ5qErxNamQWrGElpHHR8dz3fqkPp5cjqYRcxztPhtCxwkNO5lPRZPIngD7c4CNN1nKV9rO1+a4ALDZJzOKYZSybwOYWkKfEC9+ZNQz2mk98TNECgYEAtdXcba2ayxb6BypunV8xXgX7e57b7VmwSARJGjGAwJJ+Y9drFTFrn/Ei+Tl6r+lnjB7fDejlzxCKQDtP+ZwEMWKaE+LWiHDMucAC5x77wlaWzLEMSpfBsNp2Sw9PmAoQrteHFKJ6V03jMJR2SB3ihH1uj99+4i6U9svHa+Y0cHsCgYAqSMGYbHFJrdAzDEnnv163t+hkuLVbDPfwr6B4gItBGqYPP20a/PQ/5ekqcm/F/HlMCOo4x8IJYTy+cip1hkVlL9FrytcY5tYGF4gfpscIh+S0twRhVse7sK0aYD4vWGIEq9ViNrHIoGSwDIV3m19fHbp20ZDg9nGEG1VqT+chQQKBgQCukOr2rJUP5jYRDQ52rL3TO0EMlB0lR1ZyPRMFP4MQ3VLTfSrDhNeDDFf8Fc+P9C7BmZ2gV/fQC1v+gDKuOrbXxoaPEtXM1SXmKc1fnyIpl0Fcr5hSQFDl/UVVmBRcrUUI5cc76yQyjobOy6imYXJmqZisLO8+K2Q9brM8BhxP4wKBgQDd0r1LAXXDmfEdtZAz0kfhssWVb2E/w+TSyvSqFsvJh8bCrZ18WgUeiAya47mjXCUqB8dOUTPjtO8HqfUfqI+Qr0agdErt1BvoBD10ntVowbOJrj0RJLfFr8YLrHhGkLPTZzt17upv+l5x+pyMaDPxePXO8jy1Nk3rL2okpHqW6A==";
        String publicKeyA= "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtWG09zuEXdheXe0f/utXJVAbxraZ2GMw7FAplSwmJxNcwjUGsIuYGvnKXYMmnzixSXFRn3BxLrIV4Cd1xadJq4kQipwYgNDsKDWtLFWcluOni7GIccemjZnLH5NOMVDUedhWEQ94Ly6XyFx5VuGYm/RbWCU4Y16OpEi62W4dn/YGmDZScyZ/LVpYyN7D4M4L71QrT28BTluQ+z7e5/57C9wFDOWaiLUQb5ZHo4rW3Y2JYNllyQOqQ2LQMTvfosIg7H7UKhfEM14mQ0wRK/o6ij5N2aWKbhPJ10lqcf9XOLo4SOkfvwlb4+DxHHHw7PcdnbrFS4FXWIF8RWOI8shYawIDAQAB";
        // 初始化B公司的公钥、私钥
        String privateKeyB="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCD9IqMkrTxcsS/ldiY4ryZzTPY2mPiqjh6bTWCfR1+TJfh7cH9H7TH3cPWfizwa/nb9JaAqpZFEmkn1aprNKkjnZM6F1bW9F5N02mARxxUUL4J+BCnYjNwj/f25b8VQp6XrA37P3mi2Z97m9xBdGSooLCPhsFKFzq99wCJHzBivGCmlvr9IgFbsiYkjdOuMn4EFFhGqubfazECrbpVYQPzq0eeEOOal11ACmMysTinHg9DEG4X73Q9lT5Cwb38sm7mmq6e8PkWUDzKbG+CQxG/IBEio35/OB7zNcJ9G87eUIK0s5OVApxdBGFBfG6gHOjx+iTwJyEpZziZljLZf+07AgMBAAECggEAcHYHax1R54IoGxyr2ElN8ksIiCZBARqQPg87jrXxYAYJYD2RmpjeK4Ve9jzdMh7keUkwyAIbRk0VoCSjNPFRwg/3PYhMs7DberrW7MQuadHS1Y49K1svehzJefx+6bC4l43E8MA9e8S7/s5sXsDDVVB2iiyQKgQ4RVTxeOxDAbJOOSj5QZxqwR4x2JalDGFawhKsX9d2l1fQAV/JdDKKK1xqgRzvWqub9MhZzH9Cf1o+gqQ20paFwAiuHVfJpc1QjgdHI9ExDn45jqAhxe6C+YqeUVHrl0cFaCMnrSkQAcvTUT6ancZY3QW/yJlil3boQojmy+P2RkFESqnEz3EWqQKBgQDL1y7AAfUMsdjYXE+hnADuQznAv01IwcoNYmInXGhGTiAwfpQPNYsi/+u4ia0l1Myu1SVt1QcDdemLEo5YKAQepHHJ6Zi/CZNvfKSfKhbnSzDJzxBX3garSdV65rkLMCPxOEFDZtoF//kgDQg3Pl0fsHORw5isvbHqfFb689sP7wKBgQCluGz0MID2Fh/29Q001toQ1wRHjpuo5FdKn+NkWfDCdaZX3T0XmyF9vNpQM02HsC7Dpipakk/ex/ZJr277DRCWP5WuhjMHgIT6KUannMT4ARj0hmEqdwiAYlZGcK9PKhpERWDrlG4tXO+MsLAiBRA/R0SPZyeuybtoVxZ+XBOrdQKBgBa9T5LN5Rdgc69XRO7jZ1stFrFA5g86xx6NHiQD9AH7zy15wWyc7YOfBx3UBgPVZGwQjRdej2S8b6w7CrQc6rC8aZE7xPVyzQa/4eF7oYh14+BvgAQ9WYMcPntyZ6wu16OeQfGR9Lp8nr8LlSIMi/jwB0R4jHygwMtGybE/KO3zAoGAByqMe5rFWN7oDvPV2F0aHPRPWRDPpmQJak6ztJk3EhGoWdBVXfn1XsyV3DX7ukxiGU/MMRJ2pbmb6t/NOsn716N0Q5HGDyL9nRu/1GuxENBrVdehyAZRtcuK04z358J4VE9zu3w1r3x/V2QkmbQCWdopGtJpIuPSWNa7QEeXCikCgYAri2pTViyR3o98uGpCxVLFp8zLF6YHh8ufjGyhp/l/wk928rmXJM5nSlJYuFJ7MgvOjeJK6u0ebGDX58lvuSN6o6EeFWw3H/WFxEqHAJETpkOl7ya7hbJABCFzL2xG5oCDHLzpykENUrIs2ZpmvND5yiFwqfjqThmKzXcH6oBS7w==";
        String publicKeyB="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg/SKjJK08XLEv5XYmOK8mc0z2Npj4qo4em01gn0dfkyX4e3B/R+0x93D1n4s8Gv52/SWgKqWRRJpJ9WqazSpI52TOhdW1vReTdNpgEccVFC+CfgQp2IzcI/39uW/FUKel6wN+z95otmfe5vcQXRkqKCwj4bBShc6vfcAiR8wYrxgppb6/SIBW7ImJI3TrjJ+BBRYRqrm32sxAq26VWED86tHnhDjmpddQApjMrE4px4PQxBuF+90PZU+QsG9/LJu5pqunvD5FlA8ymxvgkMRvyARIqN+fzge8zXCfRvO3lCCtLOTlQKcXQRhQXxuoBzo8fok8CchKWc4mZYy2X/tOwIDAQAB";
        // 初始化请求、响应数据
        String req ="1111111111111";
        String resp ="22222222222222";

        /**
         * (公钥加密，私钥解密；私钥签名，公钥验签。)
         * 模拟A公司向B公司发送请求
         * B公司验签后解密请求报文
         * B公司处理完自己的业务后
         * 并对响应报文加密和加签
         * A公司收到B公司响应后
         * A公司验签后解密响应
         */
        //请求
        System.out.println("A向B 发送请求开始==========");
        System.out.println("加密前的请求数据为===>"+req);
        //用B公司的公钥加密请求
        String encryptByPublicKeyB = Rsa2Utils.buildRSAEncryptByPublicKey(req, publicKeyB);
        System.out.println("用B公司的公钥加密请求===>"+encryptByPublicKeyB);
        //用A公司的私钥生成签名
        String reqSignature = Rsa2Utils.buildRSASignByPrivateKey(encryptByPublicKeyB, privateKeyA);
        System.out.println("用A公司的私钥生成签名===>"+reqSignature);

        System.out.println("B收到A的请求开始==========");
        //用A公司的公钥验证签名
        boolean SignatureBoolByPublicKeyA = Rsa2Utils.buildRSAverifyByPublicKey(encryptByPublicKeyB, publicKeyA, reqSignature);
        System.out.println("用A公司的公钥验证签名===>"+SignatureBoolByPublicKeyA);
        //用B公司的私钥解密请求
        String decryptByPrivateKeyB = Rsa2Utils.buildRSADecryptByPrivateKey(encryptByPublicKeyB, privateKeyB);
        System.out.println("用B公司的私钥解密请求===>"+decryptByPrivateKeyB);


        //响应
        System.out.println("B发送A的响应开始==========");
        System.out.println("加密前的响应数据为===>"+resp);
        //用A公司的公钥加密响应
        String encryptByPublicKeyA = Rsa2Utils.buildRSAEncryptByPublicKey(resp, publicKeyA);
        System.out.println("用A公司的公钥加密请求===>"+encryptByPublicKeyA);
        //用B公司的私钥生成签名
        String respSignature = Rsa2Utils.buildRSASignByPrivateKey(encryptByPublicKeyA, privateKeyB);
        System.out.println("用B公司的私钥生成签名===>"+respSignature);

        System.out.println("A收到B的响应开始==========");
        //用B公司的公钥验证签名
        boolean SignatureBoolByPublicKeyB = Rsa2Utils.buildRSAverifyByPublicKey(encryptByPublicKeyA, publicKeyB, respSignature);
        System.out.println("用B公司的公钥验证签名===>"+SignatureBoolByPublicKeyB);
        //用A公司的私钥解密响应
        String decryptByPrivateKeyA = Rsa2Utils.buildRSADecryptByPrivateKey(encryptByPublicKeyA, privateKeyA);
        System.out.println("用A公司的私钥解密请求===>"+decryptByPrivateKeyA);
    }

}

