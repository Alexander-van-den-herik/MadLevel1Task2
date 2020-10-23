package nl.herika.level1task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.view.*
import nl.herika.level1task2.databinding.ActivityMainBinding
import androidx.core.view.forEach as forEachTable

/**
 * This is a mobile app where the is a truth table and you have to check if the answer that is given is correct
 *
 * @author Alexander van den Herik  500794135
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.submit.setOnClickListener {
            andTableCheck()
        }

    }

    /**
     * This is the method that is used for checking every row in the table exept for the row that has the id
     * @id=TableHead. This one will be skipped so that you can give information about the Table
     * The {@Link TableRow} will be send to {@Link #checkTableRow(TableRow)} so that method can be check if
     * the row is answered correctly. And if it is correct ups the counter of correct amount
     */
    fun andTableCheck() {
        var correctAmount: Int = 0
        val rows: List<TableRow> = ArrayList<TableRow>()
        for (view in binding.tableLayout.children) {
            if (view is TableRow) {
                if (view.id != R.id.TableHead) {
                    if (checkTableRow(view)){
                        correctAmount++;
                    }
                }
            }
        }
        Toast.makeText(this, String.format("You have %d, answers correct", correctAmount), Toast.LENGTH_LONG).show();
    }

    /**
     *
     * @param TableRow the TableRow that you want to check. It checks if there are columns with the tag first_column and second_column
     */
    fun checkTableRow(view: TableRow): Boolean {
        var firstColumn: String = ""
        var secondColumn: String = ""
//        val view = findViewById<TableRow>(id) ?: return false
        for (child in view.children) {
            if (child.tag.equals("first_column")){
                if (child is TextView) {
                    firstColumn = child.text.toString()
                }
            }
            if (child.tag.equals("second_column")){
                if (child is TextView) {
                    secondColumn = child.text.toString()
                }
            }
            if (child.tag.equals("answer_column")){
                if (child is EditText){
                    if (!(firstColumn.equals("") && secondColumn.equals(""))){
                        val answer = child.text.toString()
                        if (firstColumn.equals(getString(R.string.true_value)) &&
                            secondColumn.equals(getString(R.string.true_value))) {
                            if (answer.equals(getString(R.string.true_value))) return true
                            return false
                        }
                        if (answer.equals(getString(R.string.false_value))) return true
                        return false;
                    }
                }
            }
        }
        return false
    }
}
