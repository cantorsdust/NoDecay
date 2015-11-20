//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wurmonline.cantorsdust.mods;

/*
TODO:  Figure out how to import a blacklist from the .properties file.  ago says CreatureAgeMod had the same problem and implemented a solution.
TODO:  Once that is done, figure out how to check whether a given item is on a deed, and make a config that turns off decay only on deeded items.
 */

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.System;

import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.PreInitable;
import org.gotti.wurmunlimited.modloader.interfaces.WurmMod;

import com.wurmonline.server.items.ItemTemplate;
import com.wurmonline.server.items.ItemTemplateFactory;
import com.wurmonline.server.items.Item;

public class NoDecay implements WurmMod, Configurable, PreInitable {
    private Logger _logger = Logger.getLogger(this.getClass().getName());
    private boolean _noItemDecayOn = true;
    private boolean _noBridgeDecayOn = true;
    private boolean _noFenceDecayOn = true;
    private boolean _noFloorDecayOn = true;
    private boolean _noWallDecayOn = true;
    private static Set<Integer> blacklist = new HashSet(Arrays.asList(new Integer[]{null}));

    public NoDecay() {
    }

    public void configure(Properties properties) {
        this._noItemDecayOn = Boolean.valueOf(properties.getProperty("noItemDecayOn", Boolean.toString(this._noItemDecayOn))).booleanValue();
        this.Log("No Item Decay On: ", this._noItemDecayOn);
        this._noBridgeDecayOn = Boolean.valueOf(properties.getProperty("noBridgeDecayOn", Boolean.toString(this._noBridgeDecayOn))).booleanValue();
        this.Log("No Bridge Decay On: ", this._noBridgeDecayOn);
        this._noFenceDecayOn = Boolean.valueOf(properties.getProperty("noFenceDecayOn", Boolean.toString(this._noFenceDecayOn))).booleanValue();
        this.Log("No Fence Decay On: ", this._noFenceDecayOn);
        this._noFloorDecayOn = Boolean.valueOf(properties.getProperty("noFloorDecayOn", Boolean.toString(this._noFloorDecayOn))).booleanValue();
        this.Log("No Floor Decay On: ", this._noFloorDecayOn);
        this._noWallDecayOn = Boolean.valueOf(properties.getProperty("noWallDecayOn", Boolean.toString(this._noWallDecayOn))).booleanValue();
        this.Log("No Wall Decay On: ", this._noWallDecayOn);
        String blackliststring = properties.getProperty("blackliststring", "EMPTY BLACKLIST!!!");
        this.Log(("blacklist string from properties is :" + blackliststring), true);

        String[] items = blackliststring.replaceAll("\\[", "").replaceAll("\\]", "").split(",");
        int[] results = new int[items.length];
        blacklist = new HashSet();
        for (int i = 0; i < items.length; i++) {
            results[i] = Integer.parseInt(items[i]);
            blacklist.add(results[i]);
        }
        //int[] _blacklist = results;
        this.Log(("blacklist modified string for array parsing is: " + Arrays.toString(items)), true);
        this.Log(("blacklist array created from string is: " + Arrays.toString(results)), true);



        //this._blacklist = Arrays.stream(str.substring(1, str.length()-1).split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        //int testint = 74;

        //boolean testvalue = useLoop(_blacklist, 74);
        //this.Log(("blacklist contains 74 is: "), testvalue);
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
/*

############### OLD Decay function

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
*/
    /*
    private static boolean checkBlacklist(int targetValue) {
        for(int s: _blacklist){
            if(s == targetValue)
                return true;
        }
        return false;
    }
    */

/*
    private long returnDecay(int[] arr, int targetValue) {
        if (!useLoop(_blacklist, this.getTemplateId())) {
            return 9223372036854775807L;
        }
        else { return this.template.getDecayTime(); }
    }
*/
/*
    private void NoItemDecayFunction() {
        try {
            ClassPool classPool = HookManager.getInstance().getClassPool();
            classPool.importPackage("com.wurmonline.cantorsdust.mods.NoDecay");
            CtClass ex = HookManager.getInstance().getClassPool().get("com.wurmonline.server.items.Item");
            CtMethod method = ex.getDeclaredMethod("getDecayTime");
            method.insertBefore("{ import com.wurmonline.server.cantorsdust.mods.NoDecay; " +
                    "if (!(checkBlacklist(this.getTemplateId()))) {" +
                    " return 9223372036854775807L; } }");
            method = null;
            ex = null;
        } catch (NotFoundException | CannotCompileException var4) {
            throw new HookException(var4);
        }
    }
*/


    /*
    public static long getAdjustedDecayTime(ItemTemplate itemTemplate) {
        long decayTime = itemTemplate.getDecayTime();
        int templateId = itemTemplate.getTemplateId();
        if (!(blacklist.contains(Integer.valueOf(templateId)))) {
            decayTime = 9223372036854775807L;
        }
        return decayTime;
    }
    */





    public static long getAdjustedDecayTime(ItemTemplate itemTemplate, long dt) {
        long value = dt;
        int templateId = itemTemplate.getTemplateId();
        if (!(blacklist.contains(Integer.valueOf(templateId)))) {
            value = 9223372036854775807L;
        }
        return value;
    }



    /*
    private void NoItemDecayFunction() {
        try {
            ClassPool classPool = HookManager.getInstance().getClassPool();
            CtClass ex = HookManager.getInstance().getClassPool().get("com.wurmonline.server.items.Item");
            ClassFile cf = ex.getClassFile();
            //CtClass[] parameters = new CtClass[]{CtPrimitiveType.intType, CtPrimitiveType name, String plural, String itemDescriptionSuperb, String itemDescriptionNormal, String itemDescriptionBad, String itemDescriptionRotten, String itemDescriptionLong, short[] itemTypes, short imageNumber, short behaviourType, int combatDamage, long decayTime, int centimetersX, int centimetersY, int centimetersZ, int primarySkill, byte[] bodySpaces, String modelName, float difficulty, int weightGrams, byte material};
            CtMethod method = ex.getDeclaredMethod("getDecayTime");
            //method.setBody("{ int tid = this.getTemplateId();  if (!(tid == 272 || tid == 419 || tid == 420 || tid == 464 || tid == 465 || tid == 466 || tid == 766 || tid == 767 )) {" +
            //        " return 9223372036854775807L; }" +
            //        "else { return this.template.getDecayTime(); } }");
            method.setBody("{ int tid = this.getTemplateId();  if (!(tid == 272 || tid == 419 || tid == 420 || tid == 464 || tid == 465 || tid == 466 || tid == 766 || tid == 767 )) {" +
                    " return 9223372036854775807L; }" +
                    "else { return this.template.getDecayTime(); } }");
            //methodInfo.rebuildStackMapIf6(classPool, cf);
            //methodInfo.rebuildStackMap(classPool);
            method = null;
            //parameters = null;
            ex = null;
        } catch (NotFoundException | CannotCompileException var4) {
            throw new HookException(var4);
        }
    }
    */

    private void NoItemDecayFunction() {
        try {
            ClassPool classPool = HookManager.getInstance().getClassPool();
            CtClass ex = HookManager.getInstance().getClassPool().get("com.wurmonline.server.items.ItemTemplate");
            classPool.importPackage("com.wurmonline.cantorsdust.mods;");
            ClassFile cf = ex.getClassFile();
            //CtClass[] parameters = new CtClass[]{CtPrimitiveType.intType, CtPrimitiveType name, String plural, String itemDescriptionSuperb, String itemDescriptionNormal, String itemDescriptionBad, String itemDescriptionRotten, String itemDescriptionLong, short[] itemTypes, short imageNumber, short behaviourType, int combatDamage, long decayTime, int centimetersX, int centimetersY, int centimetersZ, int primarySkill, byte[] bodySpaces, String modelName, float difficulty, int weightGrams, byte material};
            CtMethod method = ex.getDeclaredMethod("getDecayTime");
            //method.setBody("{ int tid = this.getTemplateId();  if (!(tid == 272 || tid == 419 || tid == 420 || tid == 464 || tid == 465 || tid == 466 || tid == 766 || tid == 767 )) {" +
            //        " return 9223372036854775807L; }" +
            //        "else { return this.template.getDecayTime(); } }");
            //method.setBody("{ int d = this.decayTime;\n " +
            //        "return com.wurmonline.cantorsdust.mods.NoDecay.getAdjustedDecayTime(this,d);}");
            method.setBody("{ long d = this.decayTime; \n return com.wurmonline.cantorsdust.mods.NoDecay.getAdjustedDecayTime(this, d);}");
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

