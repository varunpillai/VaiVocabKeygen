package com.twmacinta.util;

public class MD5
{
  private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
  private MD5State state;
  private MD5State finals;
  private static final byte[] padding = { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

  private final void decode(byte[] buffer, int shift, int[] out)
  {
    out[0] = (buffer[shift] & 0xFF | (buffer[(shift + 1)] & 0xFF) << 8 | (buffer[(shift + 2)] & 0xFF) << 16 | buffer[(shift + 3)] << 24);

    out[1] = (buffer[(shift + 4)] & 0xFF | (buffer[(shift + 5)] & 0xFF) << 8 | (buffer[(shift + 6)] & 0xFF) << 16 | buffer[(shift + 7)] << 24);

    out[2] = (buffer[(shift + 8)] & 0xFF | (buffer[(shift + 9)] & 0xFF) << 8 | (buffer[(shift + 10)] & 0xFF) << 16 | buffer[(shift + 11)] << 24);

    out[3] = (buffer[(shift + 12)] & 0xFF | (buffer[(shift + 13)] & 0xFF) << 8 | (buffer[(shift + 14)] & 0xFF) << 16 | buffer[(shift + 15)] << 24);

    out[4] = (buffer[(shift + 16)] & 0xFF | (buffer[(shift + 17)] & 0xFF) << 8 | (buffer[(shift + 18)] & 0xFF) << 16 | buffer[(shift + 19)] << 24);

    out[5] = (buffer[(shift + 20)] & 0xFF | (buffer[(shift + 21)] & 0xFF) << 8 | (buffer[(shift + 22)] & 0xFF) << 16 | buffer[(shift + 23)] << 24);

    out[6] = (buffer[(shift + 24)] & 0xFF | (buffer[(shift + 25)] & 0xFF) << 8 | (buffer[(shift + 26)] & 0xFF) << 16 | buffer[(shift + 27)] << 24);

    out[7] = (buffer[(shift + 28)] & 0xFF | (buffer[(shift + 29)] & 0xFF) << 8 | (buffer[(shift + 30)] & 0xFF) << 16 | buffer[(shift + 31)] << 24);

    out[8] = (buffer[(shift + 32)] & 0xFF | (buffer[(shift + 33)] & 0xFF) << 8 | (buffer[(shift + 34)] & 0xFF) << 16 | buffer[(shift + 35)] << 24);

    out[9] = (buffer[(shift + 36)] & 0xFF | (buffer[(shift + 37)] & 0xFF) << 8 | (buffer[(shift + 38)] & 0xFF) << 16 | buffer[(shift + 39)] << 24);

    out[10] = (buffer[(shift + 40)] & 0xFF | (buffer[(shift + 41)] & 0xFF) << 8 | (buffer[(shift + 42)] & 0xFF) << 16 | buffer[(shift + 43)] << 24);

    out[11] = (buffer[(shift + 44)] & 0xFF | (buffer[(shift + 45)] & 0xFF) << 8 | (buffer[(shift + 46)] & 0xFF) << 16 | buffer[(shift + 47)] << 24);

    out[12] = (buffer[(shift + 48)] & 0xFF | (buffer[(shift + 49)] & 0xFF) << 8 | (buffer[(shift + 50)] & 0xFF) << 16 | buffer[(shift + 51)] << 24);

    out[13] = (buffer[(shift + 52)] & 0xFF | (buffer[(shift + 53)] & 0xFF) << 8 | (buffer[(shift + 54)] & 0xFF) << 16 | buffer[(shift + 55)] << 24);

    out[14] = (buffer[(shift + 56)] & 0xFF | (buffer[(shift + 57)] & 0xFF) << 8 | (buffer[(shift + 58)] & 0xFF) << 16 | buffer[(shift + 59)] << 24);

    out[15] = (buffer[(shift + 60)] & 0xFF | (buffer[(shift + 61)] & 0xFF) << 8 | (buffer[(shift + 62)] & 0xFF) << 16 | buffer[(shift + 63)] << 24);
  }

  private final void transform(MD5State state, byte[] buffer, int shift, int[] decode_buf)
  {
    int a = state.state[0]; int b = state.state[1]; int c = state.state[2]; int d = state.state[3]; int[] x = decode_buf;

    decode(buffer, shift, decode_buf);

    a += (b & c | (b ^ 0xFFFFFFFF) & d) + x[0] + -680876936;
    a = (a << 7 | a >>> 25) + b;
    d += (a & b | (a ^ 0xFFFFFFFF) & c) + x[1] + -389564586;
    d = (d << 12 | d >>> 20) + a;
    c += (d & a | (d ^ 0xFFFFFFFF) & b) + x[2] + 606105819;
    c = (c << 17 | c >>> 15) + d;
    b += (c & d | (c ^ 0xFFFFFFFF) & a) + x[3] + -1044525330;
    b = (b << 22 | b >>> 10) + c;

    a += (b & c | (b ^ 0xFFFFFFFF) & d) + x[4] + -176418897;
    a = (a << 7 | a >>> 25) + b;
    d += (a & b | (a ^ 0xFFFFFFFF) & c) + x[5] + 1200080426;
    d = (d << 12 | d >>> 20) + a;
    c += (d & a | (d ^ 0xFFFFFFFF) & b) + x[6] + -1473231341;
    c = (c << 17 | c >>> 15) + d;
    b += (c & d | (c ^ 0xFFFFFFFF) & a) + x[7] + -45705983;
    b = (b << 22 | b >>> 10) + c;

    a += (b & c | (b ^ 0xFFFFFFFF) & d) + x[8] + 1770035416;
    a = (a << 7 | a >>> 25) + b;
    d += (a & b | (a ^ 0xFFFFFFFF) & c) + x[9] + -1958414417;
    d = (d << 12 | d >>> 20) + a;
    c += (d & a | (d ^ 0xFFFFFFFF) & b) + x[10] + -42063;
    c = (c << 17 | c >>> 15) + d;
    b += (c & d | (c ^ 0xFFFFFFFF) & a) + x[11] + -1990404162;
    b = (b << 22 | b >>> 10) + c;

    a += (b & c | (b ^ 0xFFFFFFFF) & d) + x[12] + 1804603682;
    a = (a << 7 | a >>> 25) + b;
    d += (a & b | (a ^ 0xFFFFFFFF) & c) + x[13] + -40341101;
    d = (d << 12 | d >>> 20) + a;
    c += (d & a | (d ^ 0xFFFFFFFF) & b) + x[14] + -1502002290;
    c = (c << 17 | c >>> 15) + d;
    b += (c & d | (c ^ 0xFFFFFFFF) & a) + x[15] + 1236535329;
    b = (b << 22 | b >>> 10) + c;

    a += (b & d | c & (d ^ 0xFFFFFFFF)) + x[1] + -165796510;
    a = (a << 5 | a >>> 27) + b;
    d += (a & c | b & (c ^ 0xFFFFFFFF)) + x[6] + -1069501632;
    d = (d << 9 | d >>> 23) + a;
    c += (d & b | a & (b ^ 0xFFFFFFFF)) + x[11] + 643717713;
    c = (c << 14 | c >>> 18) + d;
    b += (c & a | d & (a ^ 0xFFFFFFFF)) + x[0] + -373897302;
    b = (b << 20 | b >>> 12) + c;

    a += (b & d | c & (d ^ 0xFFFFFFFF)) + x[5] + -701558691;
    a = (a << 5 | a >>> 27) + b;
    d += (a & c | b & (c ^ 0xFFFFFFFF)) + x[10] + 38016083;
    d = (d << 9 | d >>> 23) + a;
    c += (d & b | a & (b ^ 0xFFFFFFFF)) + x[15] + -660478335;
    c = (c << 14 | c >>> 18) + d;
    b += (c & a | d & (a ^ 0xFFFFFFFF)) + x[4] + -405537848;
    b = (b << 20 | b >>> 12) + c;

    a += (b & d | c & (d ^ 0xFFFFFFFF)) + x[9] + 568446438;
    a = (a << 5 | a >>> 27) + b;
    d += (a & c | b & (c ^ 0xFFFFFFFF)) + x[14] + -1019803690;
    d = (d << 9 | d >>> 23) + a;
    c += (d & b | a & (b ^ 0xFFFFFFFF)) + x[3] + -187363961;
    c = (c << 14 | c >>> 18) + d;
    b += (c & a | d & (a ^ 0xFFFFFFFF)) + x[8] + 1163531501;
    b = (b << 20 | b >>> 12) + c;

    a += (b & d | c & (d ^ 0xFFFFFFFF)) + x[13] + -1444681467;
    a = (a << 5 | a >>> 27) + b;
    d += (a & c | b & (c ^ 0xFFFFFFFF)) + x[2] + -51403784;
    d = (d << 9 | d >>> 23) + a;
    c += (d & b | a & (b ^ 0xFFFFFFFF)) + x[7] + 1735328473;
    c = (c << 14 | c >>> 18) + d;
    b += (c & a | d & (a ^ 0xFFFFFFFF)) + x[12] + -1926607734;
    b = (b << 20 | b >>> 12) + c;

    a += (b ^ c ^ d) + x[5] + -378558;
    a = (a << 4 | a >>> 28) + b;
    d += (a ^ b ^ c) + x[8] + -2022574463;
    d = (d << 11 | d >>> 21) + a;
    c += (d ^ a ^ b) + x[11] + 1839030562;
    c = (c << 16 | c >>> 16) + d;
    b += (c ^ d ^ a) + x[14] + -35309556;
    b = (b << 23 | b >>> 9) + c;

    a += (b ^ c ^ d) + x[1] + -1530992060;
    a = (a << 4 | a >>> 28) + b;
    d += (a ^ b ^ c) + x[4] + 1272893353;
    d = (d << 11 | d >>> 21) + a;
    c += (d ^ a ^ b) + x[7] + -155497632;
    c = (c << 16 | c >>> 16) + d;
    b += (c ^ d ^ a) + x[10] + -1094730640;
    b = (b << 23 | b >>> 9) + c;

    a += (b ^ c ^ d) + x[13] + 681279174;
    a = (a << 4 | a >>> 28) + b;
    d += (a ^ b ^ c) + x[0] + -358537222;
    d = (d << 11 | d >>> 21) + a;
    c += (d ^ a ^ b) + x[3] + -722521979;
    c = (c << 16 | c >>> 16) + d;
    b += (c ^ d ^ a) + x[6] + 76029189;
    b = (b << 23 | b >>> 9) + c;

    a += (b ^ c ^ d) + x[9] + -640364487;
    a = (a << 4 | a >>> 28) + b;
    d += (a ^ b ^ c) + x[12] + -421815835;
    d = (d << 11 | d >>> 21) + a;
    c += (d ^ a ^ b) + x[15] + 530742520;
    c = (c << 16 | c >>> 16) + d;
    b += (c ^ d ^ a) + x[2] + -995338651;
    b = (b << 23 | b >>> 9) + c;

    a += (c ^ (b | d ^ 0xFFFFFFFF)) + x[0] + -198630844;
    a = (a << 6 | a >>> 26) + b;
    d += (b ^ (a | c ^ 0xFFFFFFFF)) + x[7] + 1126891415;
    d = (d << 10 | d >>> 22) + a;
    c += (a ^ (d | b ^ 0xFFFFFFFF)) + x[14] + -1416354905;
    c = (c << 15 | c >>> 17) + d;
    b += (d ^ (c | a ^ 0xFFFFFFFF)) + x[5] + -57434055;
    b = (b << 21 | b >>> 11) + c;

    a += (c ^ (b | d ^ 0xFFFFFFFF)) + x[12] + 1700485571;
    a = (a << 6 | a >>> 26) + b;
    d += (b ^ (a | c ^ 0xFFFFFFFF)) + x[3] + -1894986606;
    d = (d << 10 | d >>> 22) + a;
    c += (a ^ (d | b ^ 0xFFFFFFFF)) + x[10] + -1051523;
    c = (c << 15 | c >>> 17) + d;
    b += (d ^ (c | a ^ 0xFFFFFFFF)) + x[1] + -2054922799;
    b = (b << 21 | b >>> 11) + c;

    a += (c ^ (b | d ^ 0xFFFFFFFF)) + x[8] + 1873313359;
    a = (a << 6 | a >>> 26) + b;
    d += (b ^ (a | c ^ 0xFFFFFFFF)) + x[15] + -30611744;
    d = (d << 10 | d >>> 22) + a;
    c += (a ^ (d | b ^ 0xFFFFFFFF)) + x[6] + -1560198380;
    c = (c << 15 | c >>> 17) + d;
    b += (d ^ (c | a ^ 0xFFFFFFFF)) + x[13] + 1309151649;
    b = (b << 21 | b >>> 11) + c;

    a += (c ^ (b | d ^ 0xFFFFFFFF)) + x[4] + -145523070;
    a = (a << 6 | a >>> 26) + b;
    d += (b ^ (a | c ^ 0xFFFFFFFF)) + x[11] + -1120210379;
    d = (d << 10 | d >>> 22) + a;
    c += (a ^ (d | b ^ 0xFFFFFFFF)) + x[2] + 718787259;
    c = (c << 15 | c >>> 17) + d;
    b += (d ^ (c | a ^ 0xFFFFFFFF)) + x[9] + -343485551;
    b = (b << 21 | b >>> 11) + c;

    state.state[0] += a;
    state.state[1] += b;
    state.state[2] += c;
    state.state[3] += d;
    System.gc();
  }

  private final void update(MD5State stat, byte[] buffer, int offset, int length)
  {
    this.finals = null;

    if (length - offset > buffer.length) {
      length = buffer.length - offset;
    }

    int index = (int)(stat.count & 0x3F);
    stat.count += length;

    int partlen = 64 - index;
    int i;
    if (length >= partlen)
    {
      int[] decode_buf = new int[16];
      if (partlen == 64) {
        partlen = 0;
      } else {
        for (i = 0; i < partlen; i++)
          stat.buffer[(i + index)] = buffer[(i + offset)];
        transform(stat, stat.buffer, 0, decode_buf);
      }
      for (i = partlen; i + 63 < length; i += 64) {
        transform(stat, buffer, i + offset, decode_buf);
      }
      index = 0;
    } else {
      i = 0;
    }
    if (i < length) {
      int start = i;
      for (; i < length; i++)
        stat.buffer[(index + i - start)] = buffer[(i + offset)];
    }
  }

  private static final byte[] encode(int[] input, int len)
  {
    byte[] out = new byte[len];
    int j;
    for (int i = j = 0; j < len; j += 4) {
      out[j] = ((byte)(input[i] & 0xFF));
      out[(j + 1)] = ((byte)(input[i] >>> 8 & 0xFF));
      out[(j + 2)] = ((byte)(input[i] >>> 16 & 0xFF));
      out[(j + 3)] = ((byte)(input[i] >>> 24 & 0xFF));

      i++;
    }

    return out;
  }

  public MD5(byte[] data) {
    this.state = new MD5State();
    this.finals = null;
    update(data);
  }

  public final void update(byte[] buffer)
  {
    if (buffer == null)
      return;
    this.state = new MD5State();
    update(this.state, buffer, 0, buffer.length);
  }

  public final synchronized byte[] doFinal()
  {
    if (this.finals == null) {
      MD5State fin = new MD5State(this.state);
      int[] count_ints = { (int)(fin.count << 3), (int)(fin.count >> 29) };
      byte[] bits = encode(count_ints, 8);
      int index = (int)(fin.count & 0x3F);
      int padlen = index < 56 ? 56 - index : 120 - index;
      update(fin, padding, 0, padlen);
      update(fin, bits, 0, 8);

      this.finals = fin;
    }

    return encode(this.finals.state, 16);
  }

  public static final String toHex(byte[] hash)
  {
    char[] buf = new char[hash.length * 2];
    int i = 0; for (int x = 0; i < hash.length; i++) {
      buf[(x++)] = HEX_CHARS[(hash[i] >>> 4 & 0xF)];
      buf[(x++)] = HEX_CHARS[(hash[i] & 0xF)];
    }
    return new String(buf);
  }

  public static final String toBase64(byte[] data) {
    char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    char[] out = new char[(data.length + 2) / 3 * 4];
    int i = 0; for (int index = 0; i < data.length; index += 4) {
      boolean quad = false;
      boolean trip = false;

      int val = 0xFF & data[i];
      val <<= 8;
      if (i + 1 < data.length) {
        val |= 0xFF & data[(i + 1)];
        trip = true;
      }
      val <<= 8;
      if (i + 2 < data.length) {
        val |= 0xFF & data[(i + 2)];
        quad = true;
      }
      out[(index + 3)] = alphabet[64];
      val >>= 6;
      out[(index + 2)] = alphabet[64];
      val >>= 6;
      out[(index + 1)] = alphabet[(val & 0x3F)];
      val >>= 6;
      out[(index + 0)] = alphabet[(val & 0x3F)];

      i += 3;
    }

    return new String(out);
  }

  public final byte[] fingerprint(byte[] data)
  {
    update(data);
    return doFinal();
  }

  public static final boolean equals(byte[] hash1, byte[] hash2)
  {
    if (hash1 == null)
      return hash2 == null;
    if (hash2 == null)
      return false;
    int targ = 16;
    if (hash1.length < 16) {
      if (hash2.length != hash1.length)
        return false;
      targ = hash1.length;
    } else if (hash2.length < 16) {
      return false;
    }
    for (int i = 0; i < targ; i++) {
      if (hash1[i] != hash2[i])
        return false;
    }
    return true;
  }
}