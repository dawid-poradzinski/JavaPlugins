package com.rootekstudio.Guilds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipe {

    static public ShapedRecipe picaxerecipe;
    static public ShapedRecipe swordrecipe;
    static public ShapedRecipe shovelrecipe;
    static public ShapedRecipe axerecipe;
    static public ShapedRecipe helmrecipe;
    static public ShapedRecipe chestplaterecipe;
    static public ShapedRecipe legginsrecipe;
    static public ShapedRecipe bootsrecipe;

    static ItemStack picaxe = new ItemStack(Material.SEA_LANTERN, 1);
    static ItemStack sword = new ItemStack(Material.SMOOTH_QUARTZ, 1);
    static ItemStack axe = new ItemStack(Material.SMOOTH_RED_SANDSTONE, 1);
    static ItemStack shovel = new ItemStack(Material.SMOOTH_SANDSTONE, 1);
    static ItemStack helm = new ItemStack(Material.SMOOTH_STONE, 1);
    static ItemStack chestplate = new ItemStack(Material.STRIPPED_ACACIA_WOOD, 1);
    static ItemStack leggins = new ItemStack(Material.TUBE_CORAL_BLOCK, 1);
    static ItemStack boots = new ItemStack(Material.YELLOW_SHULKER_BOX, 1);
    static ItemMeta picaxeM = picaxe.getItemMeta();
    static ItemMeta swordM = sword.getItemMeta();
    static ItemMeta axeM = axe.getItemMeta();
    static ItemMeta shovelM = shovel.getItemMeta();
    static ItemMeta helmM = helm.getItemMeta();
    static ItemMeta chestplateM = chestplate.getItemMeta();
    static ItemMeta legginsM = leggins.getItemMeta();
    static ItemMeta bootsM = boots.getItemMeta();

    public static void RecipeStart() {
        Guilds plugin = Guilds.getInstance();
        picaxeM.setDisplayName(ChatColor.BLUE + "picaxeM");
        picaxe.setItemMeta(picaxeM);

        swordM.setDisplayName(ChatColor.BLUE + "SwordM");
        sword.setItemMeta(swordM);

    axeM.setDisplayName(ChatColor.BLUE+"axeM");
    axe.setItemMeta(axeM);

    shovelM.setDisplayName(ChatColor.BLUE+"shovelM");
    shovel.setItemMeta(shovelM);

    helmM.setDisplayName(ChatColor.BLUE+"helmM");
    helm.setItemMeta(helmM);

    chestplateM.setDisplayName(ChatColor.BLUE+"chestplateM");
    chestplate.setItemMeta(chestplateM);

    legginsM.setDisplayName(ChatColor.BLUE+"legginsM");
    leggins.setItemMeta(legginsM);

    bootsM.setDisplayName(ChatColor.BLUE+"bootsM");
    boots.setItemMeta(bootsM);
    //ITEMS
    NamespacedKey picaxeKey = new NamespacedKey(plugin, "picaxekey");
    picaxerecipe = new ShapedRecipe(picaxeKey, picaxe);

    picaxerecipe.shape("OKO", "KDK", "OKO");
    picaxerecipe.setIngredient('O', Material.OBSIDIAN);
    picaxerecipe.setIngredient('D', Material.DIAMOND_PICKAXE);
    picaxerecipe.setIngredient('K', Material.ANVIL);


    NamespacedKey swordKey = new NamespacedKey(plugin, "swordKey");
    swordrecipe = new ShapedRecipe(swordKey, sword);

    swordrecipe.shape("OKO", "KDK", "OKO");
    swordrecipe.setIngredient('O', Material.OBSIDIAN);
    swordrecipe.setIngredient('D', Material.DIAMOND_SWORD);
    swordrecipe.setIngredient('K', Material.ANVIL);

    Bukkit.addRecipe(swordrecipe);

    NamespacedKey axeKey = new NamespacedKey(plugin, "axekey");
    axerecipe = new ShapedRecipe(axeKey, axe);

    axerecipe.shape("OKO", "KDK", "OKO");
    axerecipe.setIngredient('O', Material.OBSIDIAN);
    axerecipe.setIngredient('D', Material.DIAMOND_AXE);
    axerecipe.setIngredient('K', Material.ANVIL);


    NamespacedKey shovelKey = new NamespacedKey(plugin, "shovelKey");
    shovelrecipe = new ShapedRecipe(shovelKey, shovel);

    shovelrecipe.shape("OKO", "KDK", "OKO");
    shovelrecipe.setIngredient('O', Material.OBSIDIAN);
    shovelrecipe.setIngredient('D', Material.DIAMOND_SHOVEL);
    shovelrecipe.setIngredient('K', Material.ANVIL);

    //ARMMOR
    NamespacedKey helmKey = new NamespacedKey(plugin, "helpkey");
    helmrecipe = new ShapedRecipe(helmKey, helm);

    helmrecipe.shape("OKO", "KDK", "OKO");
    helmrecipe.setIngredient('O', Material.OBSIDIAN);
    helmrecipe.setIngredient('D', Material.DIAMOND_HELMET);
    helmrecipe.setIngredient('K', Material.ANVIL);



    NamespacedKey chestplateKey = new NamespacedKey(plugin, "chestplateKey");
    chestplaterecipe = new ShapedRecipe(chestplateKey, chestplate);

    chestplaterecipe.shape("OKO", "KDK", "OKO");
    chestplaterecipe.setIngredient('O', Material.OBSIDIAN);
    chestplaterecipe.setIngredient('D', Material.DIAMOND_CHESTPLATE);
    chestplaterecipe.setIngredient('K', Material.ANVIL);


    NamespacedKey legginsKey = new NamespacedKey(plugin, "legginsKey");
    legginsrecipe = new ShapedRecipe(legginsKey, leggins);

    legginsrecipe.shape("OKO", "KDK", "OKO");
    legginsrecipe.setIngredient('O', Material.OBSIDIAN);
    legginsrecipe.setIngredient('D', Material.DIAMOND_LEGGINGS);
    legginsrecipe.setIngredient('K', Material.ANVIL);


    NamespacedKey bootsKey = new NamespacedKey(plugin, "bootsKey");
    bootsrecipe = new ShapedRecipe(bootsKey, boots);

    bootsrecipe.shape("OKO", "KDK", "OKO");
    bootsrecipe.setIngredient('O', Material.OBSIDIAN);
    bootsrecipe.setIngredient('D', Material.DIAMOND_BOOTS);
    bootsrecipe.setIngredient('K', Material.ANVIL);

    Bukkit.addRecipe(bootsrecipe);
    Bukkit.addRecipe(picaxerecipe);
    Bukkit.addRecipe(legginsrecipe);
    Bukkit.addRecipe(chestplaterecipe);
    Bukkit.addRecipe(helmrecipe);
    Bukkit.addRecipe(axerecipe);
    Bukkit.addRecipe(shovelrecipe);    
    }
}