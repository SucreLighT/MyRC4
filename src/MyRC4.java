public class MyRC4 {
    public static void main(String[] args) {
        MyRC4 rc4 = new MyRC4();

        String plaintext = "helloworld";
        String key = "hasdg";

        String ciphertext = rc4.encrypt(plaintext, key);
        String decryptText = rc4.encrypt(ciphertext, key);

        System.out.print(
                "����Ϊ��" + plaintext + "\n" + "��ԿΪ��" + key + "\n\n" + "����Ϊ��" + ciphertext + "\n" + "����Ϊ��" + decryptText);
    }


    /**
     * 3.�ӽ��ܹ�����һ����
     * ���ܣ�������������Կ����XOR�õ���������
     * ���ܣ�������������Կ����XOR�õ���������
     */
    public String encrypt(final String plainOrCipherText, final String key) {
        Integer[] S = new Integer[256]; // S��
        Character[] keySchedul = new Character[plainOrCipherText.length()]; // ���ɵ���Կ��
        StringBuffer ciphertext = new StringBuffer();

        ksa(S, key);
        rpga(S, keySchedul, plainOrCipherText.length());

        for (int i = 0; i < plainOrCipherText.length(); ++i) {
            ciphertext.append((char) (plainOrCipherText.charAt(i) ^ keySchedul[i]));
        }

        return ciphertext.toString();
    }


    /*1.��ʼ������S*/
    public void ksa(Integer[] s, String key) {
        for (int i = 0; i < 256; ++i) {
            s[i] = i;
        }

        int j = 0;
        for (int i = 0; i < 256; ++i) {
            j = (j + s[i] + key.charAt(i % key.length())) % 256;
            swap(s, i, j);
        }
    }


    /*2.α��������㷨*/
    public void rpga(Integer[] s, Character[] keySchedul, int plaintextLength) {
        int i = 0, j = 0;
        for (int k = 0; k < plaintextLength; ++k) {
            i = (i + 1) % 256;
            j = (j + s[i]) % 256;
            swap(s, i, j);
            keySchedul[k] = (char) (s[(s[i] + s[j]) % 256]).intValue();
        }
    }

    /*�û�*/
    public void swap(Integer[] s, int i, int j) {
        Integer mTemp = s[i];
        s[i] = s[j];
        s[j] = mTemp;
    }

}
