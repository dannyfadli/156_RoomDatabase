package com.example.myarsitektur_mvvm.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults.Thickness
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myarsitektur_mvvm.R
import androidx.compose.runtime.getValue // Penting untuk 'by'
import androidx.compose.runtime.setValue // Penting untuk 'by'

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormIsian(
//    jenisK:List<String> = listOf("Laki-Laki","Perempuan"),
//    OnSubmitBtnClick : () -> Unit

    JenisJK: List<String>,
    onSubmitButtonClicked:(MutableList<String>) -> Unit
){
    var txtNama by rememberSaveable{mutableStateOf("")}
    var txtALamat by remember { mutableStateOf("") }
    var txtGender by remember { mutableStateOf("") }

    val LisData : MutableList<String> = mutableListOf(txtNama,txtGender,txtALamat)


    Scaffold (modifier= Modifier,
        {
            TopAppBar(
                title = { Text(stringResource(id = R.string.home), color = Color.White)},
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    colorResource(id = R.color.teal_700))
            )}
    ){ isiRuang ->
        Column(modifier = Modifier.padding( isiRuang),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally){
            OutlinedTextField(
                value = txtNama,
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.width(250.dp).padding(top = 20.dp),
                label = {Text(text = "Nama Lengkap")},
                onValueChange = {
                    txtNama = it
                }
            )
            HorizontalDivider(modifier = Modifier
                .padding(20.dp)
                .width(250.dp), thickness = Thickness, color = Color.Red)
            Row {
                JenisJK.forEach { item ->
                    Row (
                        modifier = Modifier.selectable(
                            selected = txtGender == item,
                            onClick = {
                                txtGender = item
                            }
                        ).padding(end = 16.dp), // Tambahkan padding agar tidak terlalu rapat

                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        RadioButton(
                            selected = txtGender == item,
                            // OnClick RadioButton juga harus mengupdate state
                            onClick = {
                                txtGender = item
                            }
                        )
                        Text(
                            text = item,
                        )
                    }
                }
            }
            HorizontalDivider(modifier = Modifier
                .padding(20.dp)
                .width(250.dp),
                thickness = 1.dp,
                color = Color.Red
            )
            OutlinedTextField(
                value = txtALamat,
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.width(250.dp),
                label = {Text(text = "ALamat Lengkap")},
                onValueChange = {
                    txtALamat = it
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                modifier = Modifier.fillMaxWidth(1f)
                    .padding(all = 25.dp),
                onClick = {onSubmitButtonClicked(LisData)}
            ) {
                Text(stringResource(R.string.submit))
            }
        }
    }
}