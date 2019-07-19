package com.hlsofttech.enumeration;

public enum PlatformType {
    /**
     * pc android ios
     */
    PC(0, "PC"),
    ANDROID(1, "ANDROID"),
    IOS(2, "IOS");

    private final int type;
    private final String platform;

    public int getType() {
        return type;
    }

    public String getPlatform() {
        return platform;
    }

    PlatformType(int type, String platform) {
        this.type = type;
        this.platform = platform;
    }


    public static PlatformType getTypeByPlatform(String platform) {
        for (PlatformType type : PlatformType.values()) {
            if (type.platform.equalsIgnoreCase(platform)) {
                return type;
            }
        }
        return null;
    }
}
