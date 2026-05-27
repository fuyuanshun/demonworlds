# CLAUDE.md

本文件为 Claude Code (claude.ai/code) 在此仓库中工作时提供指引。

## 构建与运行

```bash
# 构建模组
./gradlew build

# 运行 Minecraft 客户端（用于手动测试）
./gradlew runClient

# 运行 Minecraft 服务端
./gradlew runServer

# 运行游戏测试
./gradlew runGameTestServer

# 运行数据生成（方块状态、模型、战利品表、配方、标签、语言文件）
./gradlew runData

# build.gradle 变更后刷新 IDE 项目文件
./gradlew idea
```

`gradle.properties` 包含模组版本、Minecraft 版本（1.21.1）和 NeoForge 版本。已配置 Parchment 映射以获取反混淆后的参数名。

## 架构

这是一个 **Minecraft NeoForge 1.21.1 模组**（`mod_id: demonworlds`，包名 `com.fys.demonworlds`）。模组添加了恶魔果实物品、自定义树木、自定义生物（壁虎）、自定义效果以及末影装备/工具。

### 入口点

- **`DemonWorlds.java`** — 主模组类。接收 `IEventBus` 和 `ModContainer`；调用所有注册方法并注册配置。
- **`DemonWorldsClient.java`** — 仅客户端入口点（使用 `@Mod(dist = Dist.CLIENT)` 注解）。注册实体渲染器、物品模型属性和配置界面。

### 注册模式

所有内容使用 NeoForge 的 **`DeferredRegister`** 系统。每个领域都有自己的注册类，包含一个静态 `register(IEventBus)` 方法，由 `DemonWorlds` 构造函数调用：

| 类 | 注册内容 |
|---|---|
| `ModItems` | 物品、工具、武器、盔甲、刷怪蛋 |
| `ModBlocks` | 方块，同时自动为每个方块创建 `BlockItem` |
| `ModEntityType` | 实体类型 |
| `ModMobEffects` | 状态效果 |
| `ModEnchantments` / `ModEnchantmentEffects` | 附魔及其效果 |
| `ModCreativeModeTabs` | 创造模式物品栏 |
| `ModDataComponents` | 自定义数据组件类型 |
| `ModPotions` | 药水配方 |

`ModBlocks.register()` 会自动为每个方块创建并注册 `BlockItem`——方块不需要单独注册物品。

### 自定义内容

- **恶魔果实**（`item/custom/DemonFruit*.java`）：基类 `DemonFruit` 继承 `Item`，食用后减少最大生命值。子类（`DemonFruitSun`、`DemonFruitMoon`、`DemonFruitLightning`）各自添加独特的效果。闪电果实通过数据包触发闪电攻击技能。
- **自定义树木**（`block/custom/`）：`GoldenTree`（黄金树）和 `DiamondTree`（钻石树）方块，包含对应的树苗、树叶、木板和去皮变种。树木生成器在 `wordgen/tree/ModTreeGrowers` 中。
- **壁虎实体**（`entity/custom/GeckoEntity.java`）：自定义 `Animal`，带有 Boss 血条，可繁殖（食物：黑暗之尘），使用 GeckoLib 动画。客户端渲染使用 `GeckoModel`/`GeckoRenderer`。
- **效果**（`effect/`）：`ModBeneficialEffect`（通用效果）、`ModSlimeEffect`（史莱姆行为）和 CURSE（诅咒）效果。

### 网络数据包

`packet/LightningAbilityPacket.java` 使用 `StreamCodec.unit()` 模式实现 `CustomPacketPayload`。数据包注册在 `event/ModNetWorkEventEvents.java` 中。

### 事件处理器

- `ModEventBusEvents` — 实体渲染器层定义、属性创建、生成位置注册
- `ModClientEventEvents` — 客户端按键输入处理（闪电技能）
- `ModMobEffectEventEvents` — 生物效果应用逻辑
- `ModBrewingEventEvents` — 酿造配方注册
- `ModNetWorkEventEvents` — 网络数据包注册

### 数据生成

`datagen/ModDataGenerators.java` 是数据生成（`runData`）的入口。Provider 覆盖了方块状态、物品模型、战利品表、配方、标签（方块/物品/附魔）、语言文件（中英文）和 datapack 内容。

### 世界生成

`wordgen/` 包含配置特征（Configured Features）、放置特征（Placed Features）、矿石放置、生物群系修改器和自定义树木生成器，用于向世界生成中添加自定义矿石和树木。

### 关键常量

`constants/ModConstants.java` 定义了 `MOD_ID`、恶魔果实生命值减少量（`DEMON_FRUIT_SUB_HEALTH = 2`）、闪电果实攻击距离、不可被清除的效果列表，以及用于持久化数据存储的 NBT 键名。

## 常用开发模式

- **添加新方块**：在 `block/custom/` 中创建方块类，在 `ModBlocks` 中使用 `register(name, constructor, properties)` 注册——这会同时创建 `BlockItem`。在 `datagen/` 中添加方块状态/模型/战利品表。
- **添加新物品**：在 `ModItems` 中使用 `ITEMS.register(name, supplier)` 注册。在 `ModItemModelProvider` 中添加物品模型。
- **添加新实体**：创建实体类，在 `ModEntityType` 中注册类型，在 `DemonWorldsClient` 或 `ModEventBusEvents` 中注册渲染器，在 `ModItems` 中注册刷怪蛋。
- **客户端/服务端隔离**：使用 `Dist.CLIENT` 注解或 `FMLEnvironment.dist` 检查来隔离仅客户端代码。`DemonWorldsClient` 会自动仅在客户端加载。
