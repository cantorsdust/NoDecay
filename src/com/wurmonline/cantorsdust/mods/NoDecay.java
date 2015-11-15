//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wurmonline.cantorsdust.mods;


import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.*;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.System;

import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.PreInitable;
import org.gotti.wurmunlimited.modloader.interfaces.WurmMod;

import com.wurmonline.server.items.ItemTemplate;
import com.wurmonline.server.items.ItemTemplateFactory;

public class NoDecay implements WurmMod, Configurable, PreInitable {
    private Logger _logger = Logger.getLogger(this.getClass().getName());
    private boolean _noItemDecayOn = true;
    private boolean _noBridgeDecayOn = true;
    private boolean _noFenceDecayOn = true;
    private boolean _noFloorDecayOn = true;
    private boolean _noWallDecayOn = true;

    public NoDecay() {
    }

    public void configure(Properties properties) {
        this._noItemDecayOn = Boolean.valueOf(properties.getProperty("noItemDecayOn", Boolean.toString(this._noItemDecayOn))).booleanValue();
        this.Log("No Decay On: ", this._noItemDecayOn);
        this._noBridgeDecayOn = Boolean.valueOf(properties.getProperty("noBridgeDecayOn", Boolean.toString(this._noBridgeDecayOn))).booleanValue();
        this.Log("No Decay On: ", this._noBridgeDecayOn);
        this._noFenceDecayOn = Boolean.valueOf(properties.getProperty("noFenceDecayOn", Boolean.toString(this._noFenceDecayOn))).booleanValue();
        this.Log("No Decay On: ", this._noFenceDecayOn);
        this._noFloorDecayOn = Boolean.valueOf(properties.getProperty("noFloorDecayOn", Boolean.toString(this._noFloorDecayOn))).booleanValue();
        this.Log("No Decay On: ", this._noFloorDecayOn);
        this._noWallDecayOn = Boolean.valueOf(properties.getProperty("noWallDecayOn", Boolean.toString(this._noWallDecayOn))).booleanValue();
        this.Log("No Decay On: ", this._noWallDecayOn);
    }

    private void Log(String forFeature, boolean activated) {
        this._logger.log(Level.INFO, forFeature + activated );
    }

    public void preInit() {
        if(this._noItemDecayOn) {
            this.NoItemDecayFunction();
        }
        if(this._noBridgeDecayOn) {
            this.NoBridgeDecayFunction();
        }
        if(this._noFenceDecayOn) {
            this.NoFenceDecayFunction();
        }
        if(this._noFloorDecayOn) {
            this.NoFloorDecayFunction();
        }
        if(this._noWallDecayOn) {
            this.NoWallDecayFunction();
        }
    }

    private void NoItemDecayFunction() {
        try {
            ClassPool classPool = HookManager.getInstance().getClassPool();
            CtClass ex = HookManager.getInstance().getClassPool().get("com.wurmonline.server.items.ItemTemplateCreator");
            ClassFile cf = ex.getClassFile();
            //CtClass[] parameters = new CtClass[]{CtPrimitiveType.intType, CtPrimitiveType.intType};
            CtMethod method = ex.getDeclaredMethod("createItemTemplate");
            //MethodInfo methodInfo = method.getMethodInfo();
            //method.insertBefore("return true;");
            method.insertBefore("{ if (!($1 == 74 || $1 == 272 || $1 == 419 || $1 == 420 || $1 == 464 || $1 == 465 || $1 == 466 || $1 == 766 || $1 == 767 )) {" +
                    " $14 = 9223372036854775807L; } }");
            //methodInfo.rebuildStackMapIf6(classPool, cf);
            //methodInfo.rebuildStackMap(classPool);
            method = null;
            //parameters = null;
            ex = null;
        } catch (NotFoundException | CannotCompileException var4) {
            throw new HookException(var4);
        }
    }

    private void NoBridgeDecayFunction() {
        try {
            ClassPool classPool = HookManager.getInstance().getClassPool();
            CtClass ex = HookManager.getInstance().getClassPool().get("com.wurmonline.server.structures.BridgePart");
            ClassFile cf = ex.getClassFile();
            //CtClass[] parameters = new CtClass[]{CtPrimitiveType.intType, CtPrimitiveType.intType};
            CtMethod method = ex.getDeclaredMethod("hasNoDecay");
            //MethodInfo methodInfo = method.getMethodInfo();
            //method.insertBefore("return true;");
            method.setBody("return true;");
            //methodInfo.rebuildStackMapIf6(classPool, cf);
            //methodInfo.rebuildStackMap(classPool);
            method = null;
            //parameters = null;
            ex = null;
        } catch (NotFoundException | CannotCompileException var4) {
            throw new HookException(var4);
        }
    }

    private void NoFenceDecayFunction() {
        try {
            ClassPool classPool = HookManager.getInstance().getClassPool();
            CtClass ex = HookManager.getInstance().getClassPool().get("com.wurmonline.server.structures.Fence");
            ClassFile cf = ex.getClassFile();
            //CtClass[] parameters = new CtClass[]{CtPrimitiveType.intType, CtPrimitiveType.intType};
            CtMethod method = ex.getDeclaredMethod("hasNoDecay");
            //MethodInfo methodInfo = method.getMethodInfo();
            //method.insertBefore("return true;");
            method.setBody("return true;");
            //methodInfo.rebuildStackMapIf6(classPool, cf);
            //methodInfo.rebuildStackMap(classPool);
            method = null;
            //parameters = null;
            ex = null;
        } catch (NotFoundException | CannotCompileException var4) {
            throw new HookException(var4);
        }
    }

    private void NoFloorDecayFunction() {
        try {
            ClassPool classPool = HookManager.getInstance().getClassPool();
            CtClass ex = HookManager.getInstance().getClassPool().get("com.wurmonline.server.structures.Floor");
            ClassFile cf = ex.getClassFile();
            //CtClass[] parameters = new CtClass[]{CtPrimitiveType.intType, CtPrimitiveType.intType};
            CtMethod method = ex.getDeclaredMethod("hasNoDecay");
            //MethodInfo methodInfo = method.getMethodInfo();
            //method.insertBefore("return true;");
            method.setBody("return true;");
            //methodInfo.rebuildStackMapIf6(classPool, cf);
            //methodInfo.rebuildStackMap(classPool);
            method = null;
            //parameters = null;
            ex = null;
        } catch (NotFoundException | CannotCompileException var4) {
            throw new HookException(var4);
        }
    }

    private void NoWallDecayFunction() {
        try {
            ClassPool classPool = HookManager.getInstance().getClassPool();
            CtClass ex = HookManager.getInstance().getClassPool().get("com.wurmonline.server.structures.Wall");
            ClassFile cf = ex.getClassFile();
            //CtClass[] parameters = new CtClass[]{CtPrimitiveType.intType, CtPrimitiveType.intType};
            CtMethod method = ex.getDeclaredMethod("hasNoDecay");
            //MethodInfo methodInfo = method.getMethodInfo();
            //method.insertBefore("return true;");
            method.setBody("return true;");
            //methodInfo.rebuildStackMapIf6(classPool, cf);
            //methodInfo.rebuildStackMap(classPool);
            method = null;
            //parameters = null;
            ex = null;
        } catch (NotFoundException | CannotCompileException var4) {
            throw new HookException(var4);
        }
    }
}

