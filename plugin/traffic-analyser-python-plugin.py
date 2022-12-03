""" Tool that consumes a Packets' size report from a network traffic trace and produces a barplot representation of its data """

# --- Data handling 
import numpy as np 
import matplotlib.pyplot as plt
import matplotlib.figure as pltfig

def by_min_packet_size_ordering(key_value_tuple):
  """ function that set ordering for items of a {x-y : z} dict"""
  size_range = key_value_tuple[0]
  return int(size_range.split('-')[0])

def process_packet_data(csv_filename: str) -> dict:
  """ function that read csv file and return an ordered representation of the data"""
  data_frame = np.loadtxt(csv_filename, dtype='str', delimiter=',', unpack=False)
  headers, values = data_frame[0], data_frame[1]
  barplot_fields = dict()
  for i in range(len(headers) - 1):
    barplot_fields[headers[i]] = float(values[i])
  return dict(
      sorted(
          barplot_fields.items(), key=by_min_packet_size_ordering
      )
  )

def build_barplot(data: dict, plot_name: str) -> pltfig.Figure:
  """ function to build a barplot from packet data stored in 'data' """
  fig = plt.figure(figsize =(10, 7))
  fig, ax = plt.subplots()
  bars = ax.bar(data.keys(), data.values(), width=0.4)
  ax.spines['top'].set_visible(False)
  ax.spines['right'].set_visible(False)
  ax.spines['left'].set_visible(False)
  ax.spines['bottom'].set_color('#DDDDDD')
  ax.tick_params(bottom=False, left=False)
  ax.set_axisbelow(True)
  ax.yaxis.grid(True, color='#EEEEEE')
  ax.xaxis.grid(False)
  bar_color = bars[0].get_facecolor()
  for bar in bars:
    ax.text(
        bar.get_x() + bar.get_width() / 2,
        bar.get_height() + 0.01,
        round(bar.get_height(), 3),
        horizontalalignment='center',
        color=bar_color,
        weight='bold'
    )
  ax.set_xlabel('IP packet size (bytes)', labelpad=15, color='#333333')
  ax.set_ylabel('% Packets', labelpad=15, color='#333333')
  ax.set_title(f'Packet size distribution [{plot_name}]', pad=15, color='#333333', weight='bold')
  fig.tight_layout()
  return fig

# --- User input parsing
import argparse

def parseArguments() -> argparse.Namespace:
    """ parse received arguments """
    parser = argparse.ArgumentParser(
                    prog = 'traffic-analyser-python-plugin.py',
                    description = 'Tool to read a packets size report and generate a barplot',
                    epilog= "Example of usage: python3 traffic-analyser-python-plugin.py -i data.csv -o out.pdf -n 'Trace X'")
    parser.add_argument('-i', '--input', required=True, type=str, help='Input file with Packets size report')
    parser.add_argument('-o', '--output', required=True, type=str, help='Output file (png or pdf extension)')
    parser.add_argument('-n', '--name', type=str, help='Report name (id of trace, for example)')
    return parser.parse_args()


# --- Main function
if __name__ == "__main__":
    arguments = parseArguments()
    data = process_packet_data(arguments.input)
    figure = build_barplot(data, arguments.name if arguments.name != None else "Unnamed trace")
    figure.savefig(arguments.output)
