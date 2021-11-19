using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Calculator
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void addToDisplay(string s)
        {
            int i = -1;

            if(txtDisplay.Text.Equals("")
                || lastChar(txtDisplay.Text).Equals(" ") 
                        )
            {                                                                                               // first char
                Console.Write("erster: ");
                if (s.Equals("0"))
                {
                    Console.WriteLine("0");
                }
                else if (s.Equals("+")
                        || s.Equals("-")
                        || s.Equals("*")
                        || s.Equals("/")
                        )
                {
                    if ( !txtDisplay.Text.Equals("") )
                    txtDisplay.Text = txtDisplay.Text.Substring(0, txtDisplay.Text.Length - 2) + s + " ";
                }
                else
                {
                    Console.WriteLine("1 - 9");
                    txtDisplay.Text += s;
                }
            }
            else
            {
                Console.WriteLine("nicht erster");
                if( int.TryParse(s, out i))
                {                                                                                       // input: 0-9
                    txtDisplay.Text += s;
                }
                else
                {                                                                                       // input: +,-,*,/
                    txtDisplay.Text += " " + s + " ";
                }
                
            }

            //Console.WriteLine(txtDisplay.Text);
            //Console.WriteLine(lastChar(txtDisplay.Text));
        }

        private string lastChar(string s)
        {
            return s.Substring(s.Length - 1, 1);
        }

        /// Numbers
        private void btn0_Click(object sender, EventArgs e)
        {
            addToDisplay(sender.ToString().Substring(sender.ToString().Length - 1, 1));
        }

        private void btn1_Click(object sender, EventArgs e)
        {
            addToDisplay(sender.ToString().Substring(sender.ToString().Length - 1, 1));
        }

        private void btn2_Click(object sender, EventArgs e)
        {
            addToDisplay( sender.ToString().Substring( sender.ToString().Length - 1, 1 ) );
        }

        private void btn3_Click(object sender, EventArgs e)
        {
            addToDisplay(sender.ToString().Substring(sender.ToString().Length - 1, 1));
        }

        private void btn4_Click(object sender, EventArgs e)
        {
            addToDisplay(sender.ToString().Substring(sender.ToString().Length - 1, 1));
        }

        private void btn5_Click(object sender, EventArgs e)
        {
            addToDisplay(sender.ToString().Substring(sender.ToString().Length - 1, 1));
        }

        private void btn6_Click(object sender, EventArgs e)
        {
            addToDisplay(sender.ToString().Substring(sender.ToString().Length - 1, 1));
        }

        private void btn7_Click(object sender, EventArgs e)
        {
            addToDisplay(sender.ToString().Substring(sender.ToString().Length - 1, 1));
        }

        private void btn8_Click(object sender, EventArgs e)
        {
            addToDisplay(sender.ToString().Substring(sender.ToString().Length - 1, 1));
        }

        private void btn9_Click(object sender, EventArgs e)
        {
            addToDisplay(sender.ToString().Substring(sender.ToString().Length - 1, 1));
        }

        /// +, -, *,
        private void btnAdd_Click(object sender, EventArgs e)
        {
            addToDisplay("+");
        }

        private void btnSubtract_Click(object sender, EventArgs e)
        {
            addToDisplay("-");
        }

        private void btnMultiply_Click(object sender, EventArgs e)
        {
            addToDisplay("*");
        }

        private void btnDivide_Click(object sender, EventArgs e)
        {
            addToDisplay("/");
        }
        
        /// Result
        private void btnResult_Click(object sender, EventArgs e)
        {
            string input = txtDisplay.Text;
            int operators = 0;

            // count operators
            for( int i = 0; i < input.Length; i++ )
            {
                string sub = input.Substring(i, 1);
                if(sub.Equals("+")
                    || sub.Equals("-")
                    || sub.Equals("*")
                    || sub.Equals("/")
                    )
                {
                    operators++;
                }
            }

            double[] terms = new double[operators];

            // divide input into #operators terms
            for (int j = 0; j < operators; j++)
            {
                //TODO in while packen!
                for (int i = 0; i < input.Length; i++)
                {
                    string sub = input.Substring(i, 1);
                    if (sub.Equals("+")
                        || sub.Equals("-")
                        || sub.Equals("*")
                        || sub.Equals("/")
                        )
                    {
                        string temp = input.Substring(0, i - 2);
                        double.TryParse(temp, out terms[j]);
                        Console.Write(terms[j] + " ");
                    }
                }
            }
            

        }

        /// Controls
        private void btnC_Click(object sender, EventArgs e)
        {
            txtDisplay.Text = "";
        }
    }
}
