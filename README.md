# 🍔 FoodApp – AI-Led Android Shopping Screen

> **Track:** Android (Java)  
> **Architecture:** MVVM with RoomDB  
> **Development Style:** AI-Led using ProxyAI (ChatGPT)

---

## 🖼️ Side-by-Side Design Comparison

<table>
  <tr>
    <th>🎯 Target Design</th>
    <th>🧪 My Implementation</th>
  </tr>
  <tr>
    <td>
      <img src="https://github.com/asmaa2001-coder/FoodOrders/blob/master/app/src/main/java/com/foodapp/screenshots/design_screen.png" width="300" />
    </td>
    <td>
      <img src="https://github.com/user-attachments/assets/bdfa0da1-4dfb-4207-9fde-513b58a84e66" width="300" />
    </td>
  </tr>
</table>

## 🎯 Objective

This demo application recreates a modern shopping screen, developed with guidance from **ProxyAI (ChatGPT)**. It uses clean MVVM architecture and local persistence via **RoomDB**.

The screen includes:
- 🧱 Product grid layout
- 🏷️ Category chips
- 🔢 Quantity selectors (+/–)
- 🍫 Snackbars for interaction feedback

---

## 💡 ProxyAI Prompts Used

Below are the ChatGPT prompts and commit hashes used to drive the development process.

---

### 🔹 `c3b567f0` – Product Card Layout (`product_item.xml`)

**Prompt:**  
> “Create a clean and responsive XML layout file for a product item card using MaterialCardView. It should include product image, brand logo, name, price, and a quantity selector.”

**Notes:**  
- Added extra padding and improved text alignment manually.
- Updated brand logo scaling from AI suggestion.

---

### 🔹 `1286fcf4` – MainActivity + RecyclerView Binding

**Prompt:**  
> “Create a MainActivity that loads products in a grid using RecyclerView. Observe products from a ViewModel using LiveData. Implement category filtering with Chips and show Snackbars on item interactions.”

**Enhancements:**  
- Hooked Chip click listeners for dynamic filtering.
- Displayed quantity changes via Snackbar.

---

### 🔹 `6b48cb8d` – ProductAdapter with Quantity Buttons

**Prompt:**  
> “Create a ProductAdapter with ViewHolder that binds product image, name, and price, and supports quantity increment/decrement with + and – buttons. Notify interactions via listener interface.”

**Integration:**  
- Implemented listener in `MainActivity`.
- Tracked quantity using a `Map<Product, Int>` approach.

---

### 🔹 `9a99e233` – ViewModel + Filtering Logic

**Prompt:**  
> “Create a ViewModel that inserts mock product data and exposes filtered results via LiveData. Add a method to filter products by category name.”

**Note:**  
- Moved ViewModel from `view/` to `viewmodel/` for better separation.

---

### 🔹 `7bcf384d` – RoomDB Integration

**Prompt:**  
> “Create a Room database with a Product entity and DAO. Include methods to insert a dummy list and retrieve products by category or all products.”

**Why Room over SQLite?**  
- 🔐 Type safety  
- ♻️ LiveData compatibility  
- 🧼 Less boilerplate

---

## 🧱 Project Structure

com.foodapp/
├── data/ # AppDatabase, ProductDao, ProductRepository,AppExecutors
├── model/ # Product.java (Entity)
├── view/ # UI (MainActivity, Adapter, layouts)
├── viewmodel/ # ProductViewModel.java


---

## 🚀 Features

- 💅 Pixel-perfect UI
- 🔄 Chip-based filtering with LiveData
- 🧠 AI-led modular generation
- 📦 MVVM + Repository pattern
- 🔗 RoomDB for mock local persistence
- 📣 Snackbars for UI feedback

---

## 🛠️ How to Run

1. **Clone the repository**
   [git clone https://github.com/asmaa2001-coder/FoodOrders](https://github.com/asmaa2001-coder/FoodOrders)
2. **Open in Android Studio**
3. **Run on emulator or device**

[Screen_recording_20250725_040130.webm](https://github.com/user-attachments/assets/4240539f-1f75-466b-8a77-a64f2319ea5c)

