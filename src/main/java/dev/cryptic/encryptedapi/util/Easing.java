package dev.cryptic.encryptedapi.util;

public class Easing {
    public static double easeInSin(double t) {
        return 1.0D - Math.cos(t * (Math.PI / 2.0D));
    }

    public static double easeOutSin(double t) {
        return Math.sin(t * (Math.PI / 2.0D));
    }

    public static double easeInOutSin(double t) {
        return -(Math.cos(Math.PI * t) - 1.0D) / 2.0D;
    }

    public static double easeInQuad(double t) {
        return t * t;
    }

    public static double easeOutQuad(double t) {
        return t * (2.0D - t);
    }

    public static double easeInOutQuad(double t) {
        return t < 0.5D ? 2.0D * t * t : 1.0D - Math.pow(-2.0D * t + 2.0D, 2.0D) / 2.0D;
    }

    public static double easeInCubic(double t) {
        return t * t * t;
    }

    public static double easeOutCubic(double t) {
        return 1.0D + Math.pow(t - 1.0D, 3.0D);
    }

    public static double easeInOutCubic(double t) {
        return t < 0.5D ? 4.0D * t * t * t : 1.0D + Math.pow(-2.0D * t + 2.0D, 3.0D) / 2.0D;
    }

    public static double easeInQuart(double t) {
        return t * t * t * t;
    }

    public static double easeOutQuart(double t) {
        return 1.0D - Math.pow(t - 1.0D, 4.0D);
    }

    public static double easeInOutQuart(double t) {
        return t < 0.5D ? 8.0D * t * t * t * t : 1.0D - Math.pow(-2.0D * t + 2.0D, 4.0D) / 2.0D;
    }

    public static double easeInQuint(double t) {
        return t * t * t * t * t;
    }

    public static double easeOutQuint(double t) {
        return 1.0D + Math.pow(t - 1.0D, 5.0D);
    }

    public static double easeInOutQuint(double t) {
        return t < 0.5D ? 16.0D * t * t * t * t * t : 1.0D + Math.pow(-2.0D * t + 2.0D, 5.0D) / 2.0D;
    }

    public static double easeInExpo(double t) {
        return t == 0.0D ? 0.0D : Math.pow(2.0D, 10.0D * t - 10.0D);
    }

    public static double easeOutExpo(double t) {
        return t == 1.0D ? 1.0D : 1.0D - Math.pow(2.0D, -10.0D * t);
    }

    public static double easeInOutExpo(double t) {
        return t == 0.0D ? 0.0D : t == 1.0D ? 1.0D : t < 0.5D ? Math.pow(2.0D, 20.0D * t - 10.0D) / 2.0D : (2.0D - Math.pow(2.0D, -20.0D * t + 10.0D)) / 2.0D;
    }

    public static double easeInCirc(double t) {
        return 1.0D - Math.sqrt(1.0D - t * t);
    }

    public static double easeOutCirc(double t) {
        return Math.sqrt(1.0D - Math.pow(t - 1.0D, 2.0D));
    }

    public static double easeInOutCirc(double t) {
        return t < 0.5D ? (1.0D - Math.sqrt(1.0D - Math.pow(2.0D * t, 2.0D))) / 2.0D : (Math.sqrt(1.0D - Math.pow(-2.0D * t + 2.0D, 2.0D)) + 1.0D) / 2.0D;
    }

    public static double easeInBack(double t) {
        return t * t * (2.70158D * t - 1.70158D);
    }

    public static double easeOutBack(double t) {
        return 1.0D + (t - 1.0D) * (t - 1.0D) * (2.70158D * (t - 1.0D) + 1.70158D);
    }

    public static double easeInOutBack(double t) {
        return t < 0.5D ? t * t * (7.0D * t - 2.5D) / 2.0D : 1.0D + (t - 1.0D) * (t - 1.0D) * (7.0D * (t - 1.0D) + 2.5D) / 2.0D;
    }

    public static double easeInElastic(double t) {
        return t == 0.0D ? 0.0D : t == 1.0D ? 1.0D : -Math.pow(2.0D, 10.0D * t - 10.0D) * Math.sin((t * 10.0D - 10.75D) * 2.0D * Math.PI / 3.0D);
    }

    public static double easeOutElastic(double t) {
        return t == 0.0D ? 0.0D : t == 1.0D ? 1.0D : Math.pow(2.0D, -10.0D * t) * Math.sin((t * 10.0D - 0.75D) * 2.0D * Math.PI / 3.0D) + 1.0D;
    }

    public static double easeInOutElastic(double t) {
        return t == 0.0D ? 0.0D : t == 1.0D ? 1.0D : t < 0.5D ? -(Math.pow(2.0D, 20.0D * t - 10.0D) * Math.sin((20.0D * t - 11.125D) * 2.0D * Math.PI / 4.5D)) / 2.0D : Math.pow(2.0D, -20.0D * t + 10.0D) * Math.sin((20.0D * t - 11.125D) * 2.0D * Math.PI / 4.5D) / 2.0D + 1.0D;
    }

    public static double easeInBounce(double t) {
        return 1.0D - easeOutBounce(1.0D - t);
    }

    public static double easeOutBounce(double t) {
        if (t < 1.0D / 2.75D) {
            return 7.5625D * t * t;
        } else if (t < 2.0D / 2.75D) {
            return 7.5625D * (t -= 1.5D / 2.75D) * t + 0.75D;
        } else if (t < 2.5D / 2.75D) {
            return 7.5625D * (t -= 2.25D / 2.75D) * t + 0.9375D;
        } else {
            return 7.5625D * (t -= 2.625D / 2.75D) * t + 0.984375D;
        }
    }

    public static double easeInOutBounce(double t) {
        return t < 0.5D ? (1.0D - easeOutBounce(1.0D - 2.0D * t)) / 2.0D : (1.0D + easeOutBounce(2.0D * t - 1.0D)) / 2.0D;
    }


}
