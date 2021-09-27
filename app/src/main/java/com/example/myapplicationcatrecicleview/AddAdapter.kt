package com.example.myapplicationcatrecicleview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationcatrecicleview.databinding.CatLayoutBinding

class AddAdapter(var list: MutableList<Cat>): RecyclerView.Adapter<CatViewHolder>(), Filterable {
    var filterList = mutableListOf<Cat>()
    init {
        filterList = list
    }
    //onCreateViewHolder: возвращает объект ViewHolder, который будет хранить данные по одному объекту.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {

        val binding = CatLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
      var catViewHolder = CatViewHolder(binding)
        return catViewHolder
    }
//onBindViewHolder: выполняет привязку объекта ViewHolder к объекту  по определенной позиции.
    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
      var cat = filterList[position]
        holder.binding.name.text = cat.name
        holder.binding.age.text = cat.age.toString()
    }
//getItemCount: возвращает количество объектов в списке
    override fun getItemCount(): Int {
  return filterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val word : String = charSequence.toString()
                if (word.isEmpty()){
                    filterList = list

                }else{
                    var resultList = ArrayList<Cat>()
                    for (cat in list){
                        if (cat.name.toUpperCase().contains(word.toString().toUpperCase())){
                            resultList.add(cat)
                        }

                    }


//                    val resultList = ArrayList<Model>()
//                    for (row in countryList) {
//                        if (row.name.toLowerCase().contains(constraint.toString().toLowerCase())) {
//                            resultList.add(row)
//                        }
//                    }
                  filterList = resultList






                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
      filterList = results?.values as MutableList<Cat>

               notifyDataSetChanged()//принудительно все обновить
            }

        }
    }

}