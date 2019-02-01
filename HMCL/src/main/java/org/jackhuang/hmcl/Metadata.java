/*
 * Hello Minecraft! Launcher
 * Copyright (C) 2019  huangyuhui <huanghongxun2008@126.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.jackhuang.hmcl;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.jackhuang.hmcl.util.io.JarUtils;
import org.jackhuang.hmcl.util.platform.OperatingSystem;

/**
 * Stores metadata about this application.
 */
public final class Metadata {
    private Metadata() {}

    public static final String VERSION = "6.1";
    public static final String NAME = "MoeLauncher";
    public static final String TITLE = NAME + " " + VERSION;
    
    public static final String UPDATE_URL = "";
    public static final String CONTACT_URL = "https://github.com/MoeNetwork/MoeCraft/issues";
    public static final String HELP_URL = "https://accounts.moecraft.net/";
    public static final String PUBLISH_URL = "https://www.moecraft.net";

    public static final String MOECRAFT_AUTHLIB_URL = "https://accounts.moecraft.net/API/Mc/authlib/";
    public static final String MOECRAFT_AUTHLIB_JSON = "{\"meta\":{\"serverName\":\"MoeCraft\",\"implementationName\":\"MoeCraft Account Center: Minecraft Yggdrasil API (Completely Implemented by Kenvix) for Authlib-Injector 1.1.23\",\"implementationVersion\":\"5.0\"},\"skinDomains\":[\".moecraft.net\",\".kenvix.com\",\"localhost\"],\"signaturePublickey\":\"-----BEGIN PUBLIC KEY-----\\nMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDYM0MxKIv4ybovs4XlwMk4g\\/0r\\nO4+HDK+ltpFDkFjiBY5pDHK4J5Z2tdtkliK7DC2mwIAK5wOeTXYb9uHO8VAVxuE9\\nenMSOiVl9uvSVPelDU\\/t\\/JH3gSE6yYT7aNHT6xpvqnSQrCUGDvTbsqXvjxEr3F\\/H\\nzORoqhiGR+F27XfOhQIDAQAB\\n-----END PUBLIC KEY-----\",\"status\":0,\"info\":\"\"}";

    public static final Path HMCL_DIRECTORY = Paths.get("./HMCLData/");
    public static final Path HMCL_LIBRARY_DIRECTORY = HMCL_DIRECTORY.resolve("Library");
    public static final Path MINECRAFT_DIRECTORY = OperatingSystem.getWorkingDirectory("minecraft");
}
